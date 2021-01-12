package it.polimi.db2.db2project.controllers;


import it.polimi.db2.db2project.entities.MarketingQuestion;
import it.polimi.db2.db2project.model.MarketingQuestionDTO;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    QuestionnaireService questionnaireService;

    @PostMapping("/newQuestionnaire")
    public ResponseEntity<?> createMarketingQuestion(@RequestBody QuestionnaireDTO questionnaireDTO) {
        return ResponseEntity.ok().build();     //TODO implementare
    }

    @PostMapping("/deleteQuestionnaire")
    public ResponseEntity<?> deleteQuestionnaire(@RequestBody Long questionnaireId) {
        return ResponseEntity.ok().build();     //TODO
    }

}
