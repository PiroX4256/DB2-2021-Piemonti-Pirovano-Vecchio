package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/home")
public class HomePageController {
    @Autowired
    QuestionnaireService questionnaireService;

    @GetMapping("/getHomePage")
    public ResponseEntity<?> index() {
        Questionnaire questionnaire = questionnaireService.findByDate(new Date());
        return ResponseEntity.ok(questionnaire);
    }
}
