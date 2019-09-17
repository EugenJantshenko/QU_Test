package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReceivedDataProcessorTest {

    private ReceivedDataProcessor processor = new ReceivedDataProcessor();

    @Test
    void shouldProcessAllRecords() {
        assertEquals(7, processor.processReceivedData("inputTest.txt"));
        assertEquals(18, processor.processReceivedData("input.txt"));
    }
}