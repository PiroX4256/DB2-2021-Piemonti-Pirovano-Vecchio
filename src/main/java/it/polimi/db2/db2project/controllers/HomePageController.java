package it.polimi.db2.db2project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class HomePageController {
    @GetMapping("/")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("Test kek");
    }
}
