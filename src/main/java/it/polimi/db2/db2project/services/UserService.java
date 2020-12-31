package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.User;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Stateless
public class UserService {
    @PersistenceContext(unitName = "QuestionnaireEJB")
    private EntityManager em;

    public User checkCredentials(String username, String password) throws Exception {
        List<User> usersList = null;
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 4096, 3);
        String hashedPassword = passwordEncoder.encode(password);
        try {
            usersList = em.createNamedQuery("User.checkCredentials", User.class).setParameter(1, username).setParameter(2, hashedPassword).getResultList();

        }
        catch (PersistenceException e) {
            throw new Exception("Could not verify credentials");
        }
        if(usersList.size() == 1) {
            return usersList.get(0);
        }
        return null;
    }

}
