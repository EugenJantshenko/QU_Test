package inputoutput;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputFileReaderTest {


    @Test
    void shouldReturnListOfRecords() {
        InputFileReader reader= new InputFileReader();
        assertEquals(19, reader.readFromTxt("input.txt").size());
    }
}