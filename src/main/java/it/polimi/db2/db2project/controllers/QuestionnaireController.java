package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.OffensiveWords;
import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.entities.StatisticalQuestion;
import it.polimi.db2.db2project.entities.User;
import it.polimi.db2.db2project.model.Answer;
import it.polimi.db2.db2project.model.AnswersDTO;
import it.polimi.db2.db2project.model.ProductDTO;
import it.polimi.db2.db2project.model.Status;
import it.polimi.db2.db2project.services.*;
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
    @Autowired
    private OffensiveWordService offensiveWordService;

    @GetMapping("/getQuestionnaireFromId")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getQuestionnaireFromId(@RequestParam Long id){
        Questionnaire questionnaire = questionnaireService.findById(id);
        if (questionnaire == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(questionnaire);
    }

    @GetMapping("/getQuestionnaireFromDate")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getQuestionnaireFromDate() {
        Questionnaire questionnaire = questionnaireService.findByDate(new Date());
        return ResponseEntity.ok(questionnaire);
    }

    @PostMapping("/newAnswers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> fillQuestionnaire(@RequestBody AnswersDTO answersDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.search(authentication.getName());
        Status status;
        if (!answersDTO.isCancelled()) {
            List<OffensiveWords> offensiveWords = offensiveWordService.getAllOffensiveWords();
            //add marketingAnswers
            for(Answer marketingAnswer : answersDTO.getMarketingAnswer()) {
                for (String word : marketingAnswer.getAnswerContent().split(" ")) {
                    if (offensiveWords.stream().filter(n -> n.getWord().equalsIgnoreCase(word)).count() > 0) {
                        userService.banUser(user);
                        return ResponseEntity.unprocessableEntity().body("There are some offensive words in your answer!");
                    }
                }
            }
            status = Status.SUBMITTED;
            //add in UserFilled
            Questionnaire questionnaire = questionnaireService.findByDate(new Date());
            userService.createUserFilled(user.getId(), questionnaire.getId(), user, questionnaire, status);
            answersDTO.getMarketingAnswer()
                    .forEach(n -> questionnaireService.createMarketingAnswer(
                            user, marketingQuestionService.findById(n.getQuestionId()), n.getAnswerContent()));

            //add statisticalAnswers
            List<StatisticalQuestion> statisticalQuestionList = statisticalQuestionService.findAll();
            Iterator<StatisticalQuestion> statisticalQuestionIterator = statisticalQuestionList.iterator();
            for(Integer statisticalAnswer: answersDTO.getStatisticalAnswer()) {
                if(statisticalAnswer != 0) {
                    int answerContent = statisticalAnswer.intValue();
                    questionnaireService.createStatisticalAnswer(user, statisticalQuestionIterator.next(), answerContent, questionnaire);
                }
            }
        }
        else {
            status = Status.CANCELLED;
            Questionnaire questionnaire = questionnaireService.findByDate(new Date());
            userService.createUserFilled(user.getId(), questionnaire.getId(), user, questionnaire, status);
        }

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
