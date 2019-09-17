package logic;

import entity.Record;
import inputoutput.InputFileReader;
import service.ObjectComparator;
import service.RecordCreator;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class ReceivedDataProcessor {

    private ObjectComparator comparator = new ObjectComparator();
    private RecordCreator creator = new RecordCreator();
    private Map<String, Map<String, List<Record>>> serviceIdMap = new HashMap<>();

    public Integer processReceivedData(String pathToInputFile) {
        InputFileReader reader = new InputFileReader();
        List<Record> collect = reader.readFromTxt(pathToInputFile).stream()
                .skip(1L)
                .map(this::processRecord)
                .collect(Collectors.toList());
        return collect.size();
    }

    private void addRecordToMap(Record record) {
        Map<String, List<Record>> innerMap = serviceIdMap.get(record.getServiceId());
        if (innerMap == null) {
            Map<String, List<Record>> waitingTimeRecordsMap = new HashMap<>();
            List<Record> records = new ArrayList<>();
            records.add(record);
            waitingTimeRecordsMap.put(record.getQuestionTypeId(), records);
            serviceIdMap.put(record.getServiceId(), waitingTimeRecordsMap);
        } else {
            List<Record> list = innerMap.get(record.getQuestionTypeId());
            if (list == null) {
                List<Record> newRecord = new ArrayList<>();
                newRecord.add(record);
                innerMap.put(record.getQuestionTypeId(), newRecord);
            } else {
                list.add(record);
            }
        }
    }

    private Record processRecord(String item) {
        try {
            Record record = creator.createRecordEntity(item);
            if (record.getFirstChar().equals("C")) {
                addRecordToMap(record);
            } else {
                calculateMatchingObjects(record);
            }
            return record;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, List<Record>>> getRecordsByServiceId(Record record) {
        if (record.getServiceId().equals("*")) {
            return new ArrayList<>(serviceIdMap.values());
        }
        return Collections.singletonList(serviceIdMap.get(record.getServiceId()));
    }

    private List<Record> getRecordsByQuestionTypeId(Record record, List<Map<String, List<Record>>> list) {
        List<Record> records = new ArrayList<>();
        if (record.getQuestionTypeId().equals("*")) {
            for (Map<String, List<Record>> map : list) {
                for (Map.Entry<String, List<Record>> entry : map.entrySet()) {
                    records.addAll(entry.getValue());
                }
            }
        } else {
            for (Map<String, List<Record>> map : list) {
                records.addAll(map.get(record.getQuestionTypeId()));
            }
        }
        return records;
    }

    private void calculateMatchingObjects(Record record) {
        Integer result = comparator.compareObjects(record, getRecordsByQuestionTypeId(record, getRecordsByServiceId(record)));
        if (result != 0) {
            System.out.println("Result: " + result);
        } else {
            System.out.println("Result: -");
        }
    }
}
