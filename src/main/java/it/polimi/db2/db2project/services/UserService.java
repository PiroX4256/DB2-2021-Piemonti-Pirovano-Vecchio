package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.entities.User;
import it.polimi.db2.db2project.entities.UserFilled;
import it.polimi.db2.db2project.model.Status;
import it.polimi.db2.db2project.model.UserDTO;
import it.polimi.db2.db2project.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Service(value = "userService")
@Stateless
public class UserService {
    @PersistenceContext
    private EntityManager em;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public User checkCredentials(String username, String password) throws Exception {
        List<User> usersList = null;
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 4096, 3);
        String hashedPassword = passwordEncoder.encode(password);
        try {
            usersList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, username).setParameter(2, hashedPassword).getResultList();

        } catch (PersistenceException e) {
            throw new Exception("Could not verify credentials");
        }
        if (usersList.size() == 1) {
            return usersList.get(0);
        }
        return null;
    }

    @Transactional
    public User save(UserDTO user) {
        User nUser = user.getUserFromDto();
        nUser.setPassword(passwordEncoder.encode(user.getPassword()));
        em.persist(nUser);
        return nUser;
    }

    @Transactional
    public UserFilled createUserFilled(Long userId, Long questionnaireId, User user, Questionnaire questionnaire, Status status) {
        UserFilled userFilled = new UserFilled(userId, questionnaireId, user, questionnaire, status);
        em.persist(userFilled);
        em.flush();
        return userFilled;
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String signup(UserDTO user) {
        save(user);
        return jwtTokenProvider.createToken(user.getUsername());
    }

    public User search(String username) {
        User user = em.createNamedQuery("User.getByUsername", User.class).setParameter(1, username).getSingleResult();
        return user;
    }

    @Transactional
    public boolean banUser(User user) {
        user.setActive(false);
        em.persist(user);
        return true;
    }
}
