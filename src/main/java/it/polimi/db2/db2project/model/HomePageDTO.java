package it.polimi.db2.db2project.model;

import java.util.List;

public class HomePageDTO {

    ProductDTO questionnaire;
    List<MarketingQuestionDTO> marketingQuestionList;
    List<String> statisticalQuestionList;

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
}
