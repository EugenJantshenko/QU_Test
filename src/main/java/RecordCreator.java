import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class RecordCreator {
    @NotNull
    @Contract(pure = true)
    private String[] splitLine(@NotNull String line) {
        return line.split("\\s+");
    }

    @NotNull
    @Contract(pure = true)
    private String[] splitElement(@NotNull String line) {
        return line.split("\\.");
    }

    @NotNull
    @Contract(pure = true)
    private String[] splitDate(@NotNull String line) {
        return line.split("-");
    }

    Record createRecordEntity(String inputLine) throws ParseException {
        Record entity = new Record();
        String[] line=splitLine(inputLine);
        entity.setFirstChar(line[0]);
        String[] service = splitElement(line[1]);
        if (service.length == 2) {
            entity.setServiceId(service[0]);
            entity.setVariationId(service[1]);
        } else if (service.length == 1) {
            entity.setServiceId(service[0]);
        }
        String[] question = splitElement(line[2]);
        if (question.length == 3) {
            entity.setQuestionTypeId(question[0]);
            entity.setCategoryId(question[1]);
            entity.setSubcategoryId(question[2]);
        } else if (question.length == 2) {
            entity.setQuestionTypeId(question[0]);
            entity.setCategoryId(question[1]);
        } else if (question.length == 1) {
            entity.setQuestionTypeId(question[0]);
        }
        entity.setResponseType(line[3]);
        String[] date = splitDate(line[4]);
        if (date.length == 2) {
            entity.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse(date[0]));
            entity.setDateTo(new SimpleDateFormat("dd.MM.yyyy").parse(date[1]));
        } else if (date.length == 1) {
            entity.setDateFrom(new SimpleDateFormat("dd.MM.yyyy").parse(date[0]));
        }
        if (line.length == 6) {
            entity.setWaitingTime(line[5]);
        }
        return entity;
    }
}
