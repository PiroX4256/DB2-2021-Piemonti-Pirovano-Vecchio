package it.polimi.db2.db2project.model;

import it.polimi.db2.db2project.entities.MarketingAnswer;
import it.polimi.db2.db2project.entities.MarketingQuestion;

import java.io.Serializable;
import java.util.List;

public class ReviewsDTO {
    private final String marketingQuestion;
    private final List<MarketingAnswer> marketingAnswerList;

    public ReviewsDTO(MarketingQuestion marketingQuestion, List<MarketingAnswer> marketingAnswerList) {
        this.marketingQuestion = marketingQuestion.getQuestionContent();
        this.marketingAnswerList = marketingAnswerList;
    }

    public String getMarketingQuestion() {
        return marketingQuestion;
    }

    public List<MarketingAnswer> getMarketingAnswerList() {
        return marketingAnswerList;
    }
}
