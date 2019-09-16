import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class QueryMatcher {

    private ObjectComparator comparator = new ObjectComparator();
    private RecordCreator creator = new RecordCreator();
    private Map<String, Map<String, List<Record>>> serviceIdMap = new HashMap<>();

    void matchQuery(String path) {
        InputFileReader reader = new InputFileReader();
        reader.readFromTxt(path).stream()
                .skip(1L)
                .map(item -> {
                    try {
                        Record record = creator.createRecordEntity(item);
                        if (record.getFirstChar().equals("C")) {
                            addValueToMap(record);
                        }
                        if (record.getFirstChar().equals("D")) {
                            queryMatching(record);
                        }
                        return record;
                    } catch (ParseException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    private void queryMatching(@NotNull Record record) {
        Integer result;
        if (record.getServiceId().equals("*") && record.getQuestionTypeId().equals("*")) {
            List<Record> recordList = new ArrayList<>();
            for (Map<String, List<Record>> innerMap : serviceIdMap.values()) {
                recordList.addAll(innerMap.values()
                        .stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList()));
            }
            result = comparator.compareLines(record, recordList);
        } else if (record.getServiceId().equals("*")) {
            List<Record> recordList = new ArrayList<>();
            for (Map<String, List<Record>> innerMap : serviceIdMap.values()) {
                recordList.addAll(innerMap.values()
                        .stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList()));
            }
            result = comparator.compareLines(record, recordList
                    .stream()
                    .filter(x -> x.getQuestionTypeId()
                            .equals(record.getQuestionTypeId()))
                    .collect(Collectors.toList()));
        } else if (record.getQuestionTypeId().equals("*")) {
            result = comparator.compareLines(record, serviceIdMap.get(record.getServiceId()).values()
                    .stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()));
        } else {
            result = comparator.compareLines(record, serviceIdMap.get(record.getServiceId()).get(record.getQuestionTypeId()));
        }
        if (result != 0) {
            System.out.println("Result = " + result);
        } else {
            System.out.println("Result = -");
        }
    }

    private void addValueToMap(@NotNull Record record) {
        Map<String, List<Record>> stringRecordEntityMap = serviceIdMap.get(record.getServiceId());
        if (stringRecordEntityMap == null) {
            Map<String, List<Record>> questionMap = new HashMap<>();
            List<Record> records = new ArrayList<>();
            records.add(record);
            questionMap.put(record.getQuestionTypeId(), records);
            serviceIdMap.put(record.getServiceId(), questionMap);
        } else {
            List<Record> list = stringRecordEntityMap.get(record.getQuestionTypeId());
            if (list == null) {
                List<Record> newRecord = new ArrayList<>();
                newRecord.add(record);
                stringRecordEntityMap.put(record.getQuestionTypeId(), newRecord);
            } else {
                list.add(record);
            }
        }
    }
}
