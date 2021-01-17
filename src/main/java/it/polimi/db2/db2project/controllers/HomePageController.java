package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.*;
import it.polimi.db2.db2project.model.*;
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
import java.util.stream.Collectors;

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
        ProductDTO productDTO = new ProductDTO(questionnaire.getProductName(), questionnaire.getProductImage(), questionnaire.getDate());
        List<MarketingQuestion> marketingQuestionList = questionnaire.getMarketingQuestions();
        List<MarketingQuestionDTO> marketingQuestionDTOList = new ArrayList<>();
        marketingQuestionList.forEach(n -> marketingQuestionDTOList.add(new MarketingQuestionDTO(n.getQuestionContent(), n.getId())));
        List<StatisticalQuestion> statisticalQuestionList = statisticalQuestionService.findAll();
        List<String> statisticalQuestions = new ArrayList<>();
        statisticalQuestionList.forEach(n -> statisticalQuestions.add(n.getQuestionContent()));
        return ResponseEntity.ok(new HomePageDTO(productDTO, marketingQuestionDTOList, statisticalQuestions));
    }

    @GetMapping("/getReviews")
    public ResponseEntity<?> getReviews() {
        List<MarketingQuestion> questionList = questionnaireService.findByDate(new Date()).getMarketingQuestions();
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        for(MarketingQuestion question : questionList) {
            List<MarketingAnswer> answers = question.getMarketingAnswer();
            if(answers != null) {
                reviewsDTOList.add(new ReviewsDTO(question, answers));
            }
        }
        if(reviewsDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewsDTOList);
    }

    @GetMapping("/getLeaderboard")
    public ResponseEntity<?> getLeaderboard() {
        Questionnaire questionnaire = questionnaireService.findByDate(new Date());
        List<UserFilled> userList = questionnaireService.findUsersFilledQuestionnaire(questionnaire.getId());
        List<UserLeaderboardDTO> userLeaderboardDTOS = new ArrayList<>();
        userList.forEach(n -> userLeaderboardDTOS.add(new UserLeaderboardDTO(n.getUser().getUsername(), n.getUser().getScore())));
        return ResponseEntity.ok(userLeaderboardDTOS);
    }
}
