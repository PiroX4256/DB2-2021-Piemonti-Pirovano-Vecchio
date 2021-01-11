package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.entities.User;
import it.polimi.db2.db2project.model.AnswersDTO;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import it.polimi.db2.db2project.services.QuestionnaireService;
import it.polimi.db2.db2project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> createQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) {
        return ResponseEntity.ok(questionnaireService.createQuestionnaire(questionnaireDTO));
    }

    @GetMapping("/getQuestionnaireFromId")
    public ResponseEntity<?> getQuestionnaireFromId(@RequestParam Long id){
        Questionnaire questionnnaire = questionnaireService.findById(id);
        if (questionnnaire == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionnnaire);
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
        return ResponseEntity.ok().build();
    }
}
