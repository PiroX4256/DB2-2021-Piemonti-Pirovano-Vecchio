package it.polimi.db2.db2project.controllers;


import it.polimi.db2.db2project.entities.MarketingQuestion;
import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.entities.UserFilled;
import it.polimi.db2.db2project.model.MarketingQuestionDTO;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.model.Status;
import it.polimi.db2.db2project.model.UserListDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/newQuestionnaire")
    public ResponseEntity<?> createMarketingQuestion(@RequestBody QuestionnaireDTO questionnaireDTO) {
        Questionnaire questionnaire = questionnaireService.createQuestionnaire(questionnaireDTO.getProductDTO());
        questionnaireDTO.getMarketingQuestions().forEach(n -> questionnaireService.createMarketingQuestion(questionnaire, n));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deleteQuestionnaire")
    public ResponseEntity<?> deleteQuestionnaire(@RequestParam Long questionnaireId) {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUsersList")
    public ResponseEntity<?> getUsersListFromQuestionnaire(@RequestParam Long questionnaireId) {
        List<UserFilled> submittedUsers = questionnaireService.findUsersByStatus(1 , questionnaireId);
        List<UserFilled> cancelledUsers = questionnaireService.findUsersByStatus(0, questionnaireId);
        List<String> submittedUsersString = new ArrayList<>();
        List<String> cancelledUsersString = new ArrayList<>();
        submittedUsers.forEach(n -> submittedUsersString.add(n.getUser().getUsername()));
        cancelledUsers.forEach(n -> cancelledUsersString.add(n.getUser().getUsername()));
        return ResponseEntity.ok(new UserListDTO(submittedUsersString, cancelledUsersString));
    }

}
