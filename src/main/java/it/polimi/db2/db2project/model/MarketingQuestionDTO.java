package it.polimi.db2.db2project.model;

public class MarketingQuestionDTO {

    private final String questionContent;
    private final Long questionId;

    public MarketingQuestionDTO(String questionContent, Long questionId) {
        this.questionContent = questionContent;
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public Long getQuestionId() {
        return questionId;
    }
}
