package it.polimi.db2.db2project.model;

public class Answer {

    private final Long questionId;
    private final String answerContent;

    public Answer(Long questionId, String answerContent) {
        this.questionId = questionId;
        this.answerContent = answerContent;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }
}
