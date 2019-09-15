import java.util.ArrayList;
import java.util.List;

class QueryHandler {

    private List<RecordEntity> recordList = new ArrayList<>();
    List<RecordEntity> validRecordList = new ArrayList<>();

    void handleQueries(String path) {
        StringToObjectParser parser = new StringToObjectParser();
        List<RecordEntity> recordEntityList = parser.parseString(path);

        for (RecordEntity record : recordEntityList) {
            if (record.getFirstChar().equals("C")) {
                recordList.add(record);
            }
            if (record.getFirstChar().equals("D")) {
                validRecordList.clear();
                System.out.println("New query");
                System.out.println(recordList.size());
                for (RecordEntity line : recordList) {
                    System.out.print(compareLines(record, line) + "  " + line.getWaitingTime());
                    System.out.println();
                    if (compareLines(record, line)) {
                        validRecordList.add(line);
                    }
                }
                showResult(validRecordList);
            }
        }
    }

    private void showResult(List<RecordEntity> validList) {
        int result = 0;
        for (RecordEntity entity : validList) {
            result += Integer.valueOf(entity.getWaitingTime());
        }
        if (validList.isEmpty()) {
            System.out.println("Result: --");
        } else {
            result = result / validList.size();
            System.out.println("Result: " + result);
        }
    }

    private boolean compareLines(RecordEntity query, RecordEntity waitingLine) {
        if (compareService(query, waitingLine)) return false;

        if (compareQuestionType(query, waitingLine)) return false;

        if (compareResponseType(query, waitingLine)) return false;

        if (compareDate(query, waitingLine)) return false;

        return true;
    }

    private boolean compareDate(RecordEntity query, RecordEntity waitingLine) {
        if (query.getDateTo() == null && !query.getDateFrom().equals(waitingLine.getDateFrom())) {
            return true;
        }
        if (query.getDateTo() != null) {
            if (waitingLine.getDateFrom().before(query.getDateFrom()) || waitingLine.getDateFrom().after(query.getDateTo())) {
                return true;
            }
        }
        return false;
    }

    private boolean compareResponseType(RecordEntity query, RecordEntity waitingLine) {
        if (!query.getResponseType().equals(waitingLine.getResponseType())) {
            return true;
        }
        return false;
    }

    private boolean compareQuestionType(RecordEntity query, RecordEntity waitingLine) {
        if (!query.getQuestionTypeId().equals("*")) {
            if (!query.getQuestionTypeId().equals(waitingLine.getQuestionTypeId())) {
                return true;
            }
            if (query.getCategoryId() != null && !query.getCategoryId().equals(waitingLine.getCategoryId())) {
                return true;
            }
            if (query.getSubcategoryId() != null && !query.getSubcategoryId().equals(waitingLine.getSubcategoryId())) {
                return true;
            }
        }
        return false;
    }

    private boolean compareService(RecordEntity query, RecordEntity waitingLine) {
        if (!query.getServiceId().equals("*")) {
            if (!query.getServiceId().equals(waitingLine.getServiceId())) {
                return true;
            }
            if (query.getVariationId() != null && !query.getVariationId().equals(waitingLine.getVariationId())) {
                return true;
            }
        }
        return false;
    }
}
