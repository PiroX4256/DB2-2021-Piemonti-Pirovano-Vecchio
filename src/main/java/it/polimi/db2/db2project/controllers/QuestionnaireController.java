package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.*;
import it.polimi.db2.db2project.model.Answer;
import it.polimi.db2.db2project.model.AnswersDTO;
import it.polimi.db2.db2project.model.ProductDTO;
import it.polimi.db2.db2project.model.Status;
import it.polimi.db2.db2project.services.MarketingQuestionService;
import it.polimi.db2.db2project.services.QuestionnaireService;
import it.polimi.db2.db2project.services.StatisticalQuestionService;
import it.polimi.db2.db2project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Status status;
        if (!answersDTO.isCancelled()) {

            //add marketingAnswers
            for(Answer marketingAnswer : answersDTO.getMarketingAnswer()) {
                MarketingQuestion marketingQuestion = marketingQuestionService.findById(marketingAnswer.getQuestionId());
                questionnaireService.createMarketingAnswer(user, marketingQuestion , marketingAnswer.getAnswerContent());
            }

            //add statisticalAnswers
            List<StatisticalQuestion> statisticalQuestionList = statisticalQuestionService.findAll();
            Iterator<StatisticalQuestion> statisticalQuestionIterator = statisticalQuestionList.iterator();
            for(Integer statisticalAnswer: answersDTO.getStatisticalAnswer()) {
                if(statisticalAnswer != 0) {
                    int answerContent = statisticalAnswer.intValue();
                    questionnaireService.createStatisticalAnswer(user, statisticalQuestionIterator.next(), answerContent);
                }
            }
            status = Status.SUBMITTED;
        }
        else {
            status = Status.CANCELLED;
        }

        //add in UserFilled
        Questionnaire questionnaire = questionnaireService.findByDate(new Date());
        userService.createUserFilled(user.getId(), questionnaire.getId(), user, questionnaire, status);

        return ResponseEntity.ok(status);
    }

    @GetMapping("/getAllQuestionnaires")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllQuestionnaires() {
        List<Questionnaire> questionnaires = questionnaireService.findAllQuestionnaires();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Questionnaire questionnaire : questionnaires) {
            ProductDTO productDTO = new ProductDTO(questionnaire.getProductName(), questionnaire.getProductImage(), questionnaire.getDate());
            productDTO.setId(questionnaire.getId());
            productDTOs.add(productDTO);
        }
        return ResponseEntity.ok(productDTOs);
    }
}
