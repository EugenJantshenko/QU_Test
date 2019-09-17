package service;

import entity.Record;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RecordCreator {

    private final Integer FIRST_CHAR = 0;
    private final Integer SERVICE = 1;
    private final Integer QUESTION = 2;
    private final Integer RESPONSE = 3;
    private final Integer DATA = 4;
    private final Integer WAITING_TIME = 5;

    private final Integer SERVICE_ID = 0;
    private final Integer VARIATION_ID = 1;
    private final Integer QUESTION_TYPE_ID = 0;
    private final Integer CATEGORY_ID = 1;
    private final Integer SUB_CATEGORY_ID = 2;
    private final Integer DATE_FROM = 0;
    private final Integer DATE_TO = 1;

    public Record createRecordEntity(String inputLine) throws ParseException {
        Record entity = new Record();
        String[] line = splitLine(inputLine);
        entity.setFirstChar(line[FIRST_CHAR]);
        String[] service = splitElement(line[SERVICE]);
        if (service.length > VARIATION_ID) {
            entity.setVariationId(service[VARIATION_ID]);
        }
        entity.setServiceId(service[SERVICE_ID]);
        String[] question = splitElement(line[QUESTION]);
        entity.setQuestionTypeId(question[QUESTION_TYPE_ID]);
        if (question.length > CATEGORY_ID) {
            entity.setCategoryId(question[CATEGORY_ID]);
        }
        if (question.length > SUB_CATEGORY_ID) {
            entity.setSubcategoryId(question[SUB_CATEGORY_ID]);
        }
        entity.setResponseType(line[RESPONSE]);
        String[] date = splitDate(line[DATA]);
        if (date.length > DATE_TO) {
            entity.setDateTo(new SimpleDateFormat("dd.MM.yyyy").parse(date[DATE_TO]));
        }
        entity.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse(date[DATE_FROM]));
        if (line.length > WAITING_TIME) {
            entity.setWaitingTime(line[WAITING_TIME]);
        }
        return entity;
    }

    private String[] splitLine(@NotNull String line) {
        return line.split("\\s+");
    }

    private String[] splitElement(@NotNull String line) {
        return line.split("\\.");
    }

    private String[] splitDate(@NotNull String line) {
        return line.split("-");
    }
}
