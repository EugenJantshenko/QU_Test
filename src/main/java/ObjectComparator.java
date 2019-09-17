import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class ObjectComparator {

    Integer compareLines(Record query, @NotNull List<Record> waitingLine) {
        List<Integer> waitingTime = new ArrayList<>();
        waitingLine.forEach(line -> {
            if (compareService(query, line)
                    && compareQuestionType(query, line)
                    && compareResponseType(query, line)
                    && compareDate(query, line)) {
                waitingTime.add(Integer.valueOf(line.getWaitingTime()));
            }
        });
        Integer result = 0;
        for (Integer val : waitingTime) {
            result += val;
        }
        if (waitingTime.size() == 0) {
            return 0;
        }
        return result / waitingTime.size();
    }

    private boolean compareDate(@NotNull Record query, Record waitingLine) {
        if (query.getDateTo() == null && query.getDateFrom().equals(waitingLine.getDateFrom())) {
            return true;
        }
        if (query.getDateTo() != null) {
            return waitingLine.getDateFrom().after(query.getDateFrom()) && waitingLine.getDateFrom().before(query.getDateTo());
        }
        return false;
    }

    private boolean compareResponseType(@NotNull Record query, @NotNull Record waitingLine) {
        return query.getResponseType().equals(waitingLine.getResponseType());
    }

    private boolean compareQuestionType(@NotNull Record query, Record waitingLine) {
        if (query.getCategoryId() == null || query.getCategoryId().equals(waitingLine.getCategoryId())) {
            if (query.getSubcategoryId() == null || query.getSubcategoryId().equals(waitingLine.getSubcategoryId())) {
                return true;
            } else return query.getSubcategoryId() == null || query.getSubcategoryId().equals(waitingLine.getSubcategoryId());
        }
        return false;
    }

    private boolean compareService(@NotNull Record query, Record waitingLine) {
        return query.getVariationId() == null || query.getVariationId().equals(waitingLine.getVariationId());
    }
}
