package service;

import entity.Record;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectComparatorTest {

    @Test
    void shouldCompareRecordAndQuery() throws ParseException {
        ObjectComparator comparator = new ObjectComparator();
        List<Record> recordList = new ArrayList<>();
        Record record = new Record();
        record.setFirstChar("C");
        record.setServiceId("1");
        record.setVariationId("1");
        record.setQuestionTypeId("8");
        record.setCategoryId("15");
        record.setSubcategoryId("1");
        record.setResponseType("P");
        record.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse("17.10.2012)"));
        record.setWaitingTime("83");
        Record query = new Record();
        query.setFirstChar("D");
        query.setServiceId("1");
        query.setVariationId("1");
        query.setQuestionTypeId("8");
        query.setCategoryId("15");
        query.setSubcategoryId("1");
        query.setResponseType("P");
        query.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse("15.10.2012)"));
        query.setDateTo(new SimpleDateFormat("dd.MM.yyyy").parse("18.10.2012)"));
        recordList.add(record);
        assertEquals(83, comparator.compareObjects(query, recordList));
    }
}