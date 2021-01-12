package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.*;
import it.polimi.db2.db2project.model.Answer;
import it.polimi.db2.db2project.model.AnswersDTO;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.services.MarketingQuestionService;
import it.polimi.db2.db2project.services.QuestionnaireService;
import it.polimi.db2.db2project.services.StatisticalQuestionService;
import it.polimi.db2.db2project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private MarketingQuestionService marketingQuestionService;

    @Autowired
    private StatisticalQuestionService statisticalQuestionService;

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> createQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) {
        return ResponseEntity.ok(questionnaireService.createQuestionnaire(questionnaireDTO));
    }

    @GetMapping("/getQuestionnaireFromId")
    public ResponseEntity<?> getQuestionnaireFromId(@RequestParam Long id){
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionnaire);
    }

    @GetMapping("/getQuestionnaireFromDate")
    public ResponseEntity<?> getQuestionnaireFromDate() {
        Questionnaire questionnaire = questionnaireService.findByDate(new Date());
        return ResponseEntity.ok(questionnaire);
    }

    @PostMapping("/newAnswers")
    public ResponseEntity<?> fillQuestionnaire(@RequestBody AnswersDTO answersDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.search(authentication.getName());
        if (!answersDTO.isCancelled()) {
            for(Answer marketingAnswer : answersDTO.getMarketingAnswer()) {
                MarketingQuestion marketingQuestion = marketingQuestionService.findById(marketingAnswer.getQuestionId());
                questionnaireService.createMarketingAnswer(user, marketingQuestion , marketingAnswer.getAnswerContent());
            }
            List<StatisticalQuestion> statisticalQuestionList = statisticalQuestionService.findAll();
            Iterator<StatisticalQuestion> statisticalQuestionIterator = statisticalQuestionList.iterator();
            for(Integer statisticalAnswer: answersDTO.getStatisticalAnswer()) {
                if(statisticalAnswer != 0) {
                    int answerContent = statisticalAnswer.intValue();
                    questionnaireService.createStatisticalAnswer(user, statisticalQuestionIterator.next(), answerContent);
                }
            }
        }
        else {
        //TODO add Submitted or isCancelled
        }
        return ResponseEntity.ok().build();
    }
}
