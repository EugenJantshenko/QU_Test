package service;

import entity.Record;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

class RecordCreatorTest {

    @Test
    void shouldCreateObjectFromString() throws ParseException {
        String input = "C 1.1 8.15.1 P 15.10.2012 83";
        RecordCreator recordCreator = new RecordCreator();
        Record recordObject = new Record();
        recordObject.setFirstChar("C");
        recordObject.setServiceId("1");
        recordObject.setVariationId("1");
        recordObject.setQuestionTypeId("8");
        recordObject.setCategoryId("15");
        recordObject.setSubcategoryId("1");
        recordObject.setResponseType("P");
        recordObject.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse("15.10.2012)"));
        recordObject.setWaitingTime("83");
        assertEquals(recordObject, recordCreator.createRecordEntity(input));
    }
}