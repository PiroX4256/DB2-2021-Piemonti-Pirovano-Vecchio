package it.polimi.db2.db2project.controllers;


import it.polimi.db2.db2project.entities.MarketingQuestion;
import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.model.MarketingQuestionDTO;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/newQuestionnaire")
    public ResponseEntity<?> createMarketingQuestion(@RequestBody QuestionnaireDTO questionnaireDTO) {
        Questionnaire questionnaire = questionnaireService.createQuestionnaire(questionnaireDTO.getProductDTO());
        for(String questionContent : questionnaireDTO.getMarketingQuestions()) {
            questionnaireService.createMarketingQuestion(questionnaire, questionContent);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deleteQuestionnaire")
    public ResponseEntity<?> deleteQuestionnaire(@RequestParam Long questionnaireId) {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return ResponseEntity.ok().build();
    }

}
