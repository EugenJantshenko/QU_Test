import java.util.Date;
import java.util.Objects;

public class Record {

    private String firstChar;
    private String serviceId;
    private String variationId;
    private String questionTypeId;
    private String categoryId;
    private String subcategoryId;
    private String responseType;
    private Date dateFrom;
    private Date dateTo;
    private String waitingTime;

    String getFirstChar() {
        return firstChar;
    }

    void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    String getServiceId() {
        return serviceId;
    }

    void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    String getVariationId() {
        return variationId;
    }

    void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    String getQuestionTypeId() {
        return questionTypeId;
    }

    void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    String getCategoryId() {
        return categoryId;
    }

    void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    String getSubcategoryId() {
        return subcategoryId;
    }

    void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    String getResponseType() {
        return responseType;
    }

    void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    Date getDateFrom() {
        return dateFrom;
    }

    void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    Date getDateTo() {
        return dateTo;
    }

    void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    String getWaitingTime() {
        return waitingTime;
    }

    void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return "RecordEntity{" +
                "firstChar='" + firstChar + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", variationId='" + variationId + '\'' +
                ", questionTypeId='" + questionTypeId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", subcategoryId='" + subcategoryId + '\'' +
                ", responseType='" + responseType + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", waitingTime='" + waitingTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(firstChar, record.firstChar) &&
                Objects.equals(serviceId, record.serviceId) &&
                Objects.equals(variationId, record.variationId) &&
                Objects.equals(questionTypeId, record.questionTypeId) &&
                Objects.equals(categoryId, record.categoryId) &&
                Objects.equals(subcategoryId, record.subcategoryId) &&
                Objects.equals(responseType, record.responseType) &&
                Objects.equals(dateFrom, record.dateFrom) &&
                Objects.equals(dateTo, record.dateTo) &&
                Objects.equals(waitingTime, record.waitingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstChar, serviceId, variationId, questionTypeId, categoryId, subcategoryId, responseType, dateFrom, dateTo, waitingTime);
    }
}
