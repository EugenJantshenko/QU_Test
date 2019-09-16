import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

class ReceivedDataProcessor {

    private ObjectComparator comparator = new ObjectComparator();
    private RecordCreator creator = new RecordCreator();
    private Map<String, Map<String, List<Record>>> serviceIdMap = new HashMap<>();

    void processReceivedData(String path) {
        InputFileReader reader = new InputFileReader();
        reader.readFromTxt(path).stream()
                .skip(1L)
                .map(this::processRecord)
                .collect(Collectors.toList());
    }

    private Record processRecord(String item) {
        try {
            Record record = creator.createRecordEntity(item);
            if (record.getFirstChar().equals("C")) {
                addRecordToMap(record);
            } else {
                calculateMatchingValues(record);
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

    private void calculateMatchingValues(@NotNull Record record) {
        Integer result;
        result = comparator.compareLines(record, getRecordsByQuestionTypeId(record, getRecordsByServiceId(record)));
        if (result != 0) {
            System.out.println("Result: " + result);
        } else {
            System.out.println("Result: -");
        }
    }

    private void addRecordToMap(@NotNull Record record) {
        Map<String, List<Record>> stringEntityMap = serviceIdMap.get(record.getServiceId());
        if (stringEntityMap == null) {
            Map<String, List<Record>> waitingTimeRecordsMap = new HashMap<>();
            List<Record> records = new ArrayList<>();
            records.add(record);
            waitingTimeRecordsMap.put(record.getQuestionTypeId(), records);
            serviceIdMap.put(record.getServiceId(), waitingTimeRecordsMap);
        } else {
            List<Record> list = stringEntityMap.get(record.getQuestionTypeId());
            if (list == null) {
                List<Record> newRecord = new ArrayList<>();
                newRecord.add(record);
                stringEntityMap.put(record.getQuestionTypeId(), newRecord);
            } else {
                list.add(record);
            }
        }
    }
}
