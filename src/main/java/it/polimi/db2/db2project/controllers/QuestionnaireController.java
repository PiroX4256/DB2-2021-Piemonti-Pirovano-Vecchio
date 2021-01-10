package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/new")
    public ResponseEntity<?> createQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) {
        return ResponseEntity.ok(questionnaireService.createQuestionnaire(questionnaireDTO));
    }
}
