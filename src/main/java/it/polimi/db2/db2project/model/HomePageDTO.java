package it.polimi.db2.db2project.model;

import java.util.List;

public class HomePageDTO {

    QuestionnaireDTO questionnaire;
    List<MarketingQuestionDTO> marketingQuestionList;
    List<String> statisticalQuestionList;

    public HomePageDTO(QuestionnaireDTO questionnaire, List<MarketingQuestionDTO> questionList, List<String> statisticalQuestion) {
        this.questionnaire = questionnaire;
        this.marketingQuestionList = questionList;
        this.statisticalQuestionList = statisticalQuestion;
    }

    public QuestionnaireDTO getQuestionnaire() {
        return questionnaire;
    }

    public List<MarketingQuestionDTO> getMarketingQuestionList() {
        return marketingQuestionList;
    }
}
