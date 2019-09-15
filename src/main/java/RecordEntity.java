import java.util.Date;

public class RecordEntity {

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

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(String waitingTime) {
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
}
