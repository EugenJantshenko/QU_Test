import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

class RecordCreatorTest {

    @Test
    void createRecordEntity() throws ParseException {
        String input = "C 1.1 8.15.1 P 15.10.2012 83";
        RecordCreator recordCreator = new RecordCreator();
        Record record = new Record();
        record.setFirstChar("C");
        record.setServiceId("1");
        record.setVariationId("1");
        record.setQuestionTypeId("8");
        record.setCategoryId("15");
        record.setSubcategoryId("1");
        record.setResponseType("P");
        record.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse("15.10.2012)"));
        record.setWaitingTime("83");
        assertEquals(record, recordCreator.createRecordEntity(input));
    }
}