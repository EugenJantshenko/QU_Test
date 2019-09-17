package service;

import entity.Record;

import java.util.ArrayList;
import java.util.List;

public class ObjectComparator {

    public Integer compareObjects(Record query, List<Record> waitingLine) {
        List<Integer> waitingTimeList = new ArrayList<>();
        waitingLine.forEach(line -> {
            if (compareService(query, line)
                    && compareQuestionType(query, line)
                    && compareResponseType(query, line)
                    && compareDate(query, line)) {
                waitingTimeList.add(Integer.valueOf(line.getWaitingTime()));
            }
        });
        return calculateResult(waitingTimeList);
    }

    private boolean compareService(Record query, Record waitingLine) {
        return query.getVariationId() == null || query.getVariationId().equals(waitingLine.getVariationId());
    }

    private boolean compareQuestionType(Record query, Record waitingLine) {
        if (query.getCategoryId() == null || query.getCategoryId().equals(waitingLine.getCategoryId())) {
            if (query.getSubcategoryId() == null || query.getSubcategoryId().equals(waitingLine.getSubcategoryId())) {
                return true;
            } else
                return query.getSubcategoryId() == null || query.getSubcategoryId().equals(waitingLine.getSubcategoryId());
        }
        return false;
    }

    private boolean compareResponseType(Record query, Record waitingLine) {
        return query.getResponseType().equals(waitingLine.getResponseType());
    }

    private boolean compareDate(Record query, Record waitingLine) {
        if (query.getDateTo() == null && query.getDateFrom().equals(waitingLine.getDateFrom())) {
            return true;
        }
        if (query.getDateTo() != null) {
            return waitingLine.getDateFrom().after(query.getDateFrom()) && waitingLine.getDateFrom().before(query.getDateTo());
        }
        return false;
    }

    private Integer calculateResult(List<Integer> waitingTime) {
        Integer result = 0;
        for (Integer val : waitingTime) {
            result += val;
        }
        if (waitingTime.size() == 0) {
            return 0;
        }
        return result / waitingTime.size();
    }
}
