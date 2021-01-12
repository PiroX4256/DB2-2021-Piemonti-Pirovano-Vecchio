package it.polimi.db2.db2project.model;

import java.util.List;

public class AnswersDTO {

    private final List<Answer> marketingAnswer;
    private final List<Integer> statisticalAnswer;
    private final boolean isCancelled;

    public AnswersDTO(List<Answer> answers, List<Integer> statisticalAnswer, boolean isCancelled) {
        this.marketingAnswer = answers;
        this.statisticalAnswer = statisticalAnswer;
        this.isCancelled = isCancelled;
    }

    public List<Answer> getMarketingAnswer() {
        return marketingAnswer;
    }

    public List<Integer> getStatisticalAnswer() {
        return statisticalAnswer;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
