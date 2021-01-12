package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.MarketingAnswer;
import it.polimi.db2.db2project.entities.MarketingQuestion;
import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.entities.StatisticalQuestion;
import it.polimi.db2.db2project.model.HomePageDTO;
import it.polimi.db2.db2project.model.MarketingQuestionDTO;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.model.ReviewsDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import it.polimi.db2.db2project.services.StatisticalQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomePageController {
    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    StatisticalQuestionService statisticalQuestionService;

    @GetMapping("/getHomePage")
    public ResponseEntity<?> getQuestionnaireByDate() {
        Questionnaire questionnaire = questionnaireService.findByDate(new Date());
        if(questionnaire == null) {
            return ResponseEntity.notFound().build();
        }
        QuestionnaireDTO questionnaireDTO = new QuestionnaireDTO(questionnaire.getProductName(), questionnaire.getProductImage(), questionnaire.getDate());
        List<MarketingQuestion> marketingQuestionList = questionnaire.getMarketingQuestions();
        List<MarketingQuestionDTO> marketingQuestionDTOList = new ArrayList<>();
        for(MarketingQuestion question : marketingQuestionList) {
            marketingQuestionDTOList.add(new MarketingQuestionDTO(question.getQuestionContent(), question.getId()));
        }

        List<StatisticalQuestion> statisticalQuestionList = statisticalQuestionService.findAll();
        List<String> statisticalQuestions = new ArrayList<>();
        for(StatisticalQuestion question : statisticalQuestionList) {
            statisticalQuestions.add(question.getQuestionContent());
        }
        HomePageDTO homePage = new HomePageDTO(questionnaireDTO, marketingQuestionDTOList, statisticalQuestions);
        return ResponseEntity.ok(homePage);
    }

    @GetMapping("/getReviews")
    public ResponseEntity<?> getReviews() {
        List<MarketingQuestion> questionList = questionnaireService.findByDate(new Date()).getMarketingQuestions();
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        for(MarketingQuestion question : questionList) {
            List<MarketingAnswer> answers = question.getMarketingAnswer();
            if(answers != null) {
                ReviewsDTO reviewsDTO = new ReviewsDTO(question); //TODO
                reviewsDTOList.add(reviewsDTO);
            }
        }
        if(reviewsDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewsDTOList);
    }
}
