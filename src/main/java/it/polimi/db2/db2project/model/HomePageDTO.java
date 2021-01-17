package it.polimi.db2.db2project.model;

import java.util.List;

public class HomePageDTO {

    private final ProductDTO questionnaire;
    private final List<MarketingQuestionDTO> marketingQuestionList;
    private final List<String> statisticalQuestionList;

    public HomePageDTO(ProductDTO questionnaire, List<MarketingQuestionDTO> questionList, List<String> statisticalQuestion) {
        this.questionnaire = questionnaire;
        this.marketingQuestionList = questionList;
        this.statisticalQuestionList = statisticalQuestion;
    }

    public ProductDTO getQuestionnaire() {
        return questionnaire;
    }

    public List<MarketingQuestionDTO> getMarketingQuestionList() {
        return marketingQuestionList;
    }

    public List<String> getStatisticalQuestionList() {
        return statisticalQuestionList;
    }
}
