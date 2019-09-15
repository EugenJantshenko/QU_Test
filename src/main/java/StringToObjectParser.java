import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

class StringToObjectParser {

    List<RecordEntity> parseString(String path) {
        InputFileReader reader = new InputFileReader();
        List<RecordEntity> result = reader.readFromTxt(path).stream()
                .skip(1)
                .map(item -> {
                    try {
                        return createRecordEntity(splitLine(item));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        result.stream().forEach(System.out::println);
        return result;
    }

    private String[] splitLine(String line) {
        return line.split("\\s+");
    }

    private String[] splitElement(String line) {
        return line.split("\\.");
    }

    private String[] splitDate(String line) {
        return line.split("-");
    }

    private RecordEntity createRecordEntity(String[] line) throws ParseException {
        RecordEntity entity = new RecordEntity();
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
