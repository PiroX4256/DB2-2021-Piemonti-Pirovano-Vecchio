package it.polimi.db2.db2project.controllers;

import it.polimi.db2.db2project.model.LoginUser;
import it.polimi.db2.db2project.model.UserDTO;
import it.polimi.db2.db2project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * This class handles the authentication backend of CLup, which is composed by the sign up and login methods.
 * @author Luca Pirovano
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    /**
     * This method generates a Json Web Token (JWT), according to the RestFUL paradigm.
     * @param loginUser the user credentials (in a JSON Object form)
     * @return the authentication token to be stored on client side.
     * @throws AuthenticationException if there are some troubles with the login procedure.
     */
    @PostMapping("/signin")
    public String login(@RequestBody LoginUser loginUser) throws Exception {
        String user = userService.signin(loginUser.getUsername(), loginUser.getPassword());
        return user;
    }

    @Transactional
    @PostMapping("/signup")
    public String signup(@RequestBody UserDTO user) {
        return userService.signup(user);
    }

    @GetMapping( "/getUser")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> search(@RequestParam String username) {
        return ResponseEntity.ok(userService.search(username));
    }



}
