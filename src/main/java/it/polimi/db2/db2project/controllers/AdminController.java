package it.polimi.db2.db2project.controllers;


import it.polimi.db2.db2project.entities.MarketingQuestion;
import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.entities.UserFilled;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.model.ReviewsDTO;
import it.polimi.db2.db2project.model.Status;
import it.polimi.db2.db2project.model.UserListDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/newQuestionnaire")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createMarketingQuestion(@RequestBody QuestionnaireDTO questionnaireDTO) {
        Questionnaire questionnaire = questionnaireService.createQuestionnaire(questionnaireDTO.getProductDTO());
        questionnaireDTO.getMarketingQuestions().forEach(n -> questionnaireService.createMarketingQuestion(questionnaire, n));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deleteQuestionnaire")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteQuestionnaire(@RequestParam Long questionnaireId) {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUsersList")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUsersListFromQuestionnaire(@RequestParam Long questionnaireId) {
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        List<UserFilled> submittedUsers = questionnaireService.findUsersByStatus(Status.SUBMITTED ,questionnaire );
        List<UserFilled> cancelledUsers = questionnaireService.findUsersByStatus(Status.CANCELLED, questionnaire);
        List<String> submittedUsersString = new ArrayList<>();
        List<String> cancelledUsersString = new ArrayList<>();
        submittedUsers.forEach(n -> submittedUsersString.add(n.getUser().getUsername()));
        cancelledUsers.forEach(n -> cancelledUsersString.add(n.getUser().getUsername()));
        return ResponseEntity.ok(new UserListDTO(submittedUsersString, cancelledUsersString));
    }

    @GetMapping("/inspection")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getReviewsFromQuestionnaire(@RequestParam Long questionnaireId) {
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);
        List<ReviewsDTO> reviewsDTOList = new ArrayList<>();
        List<MarketingQuestion> marketingQuestions = questionnaire.getMarketingQuestions();
        marketingQuestions.forEach(n -> reviewsDTOList.add(new ReviewsDTO(n, n.getMarketingAnswer())));
        if(reviewsDTOList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewsDTOList);
    }
}
