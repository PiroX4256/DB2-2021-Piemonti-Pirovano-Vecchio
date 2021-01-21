package it.polimi.db2.db2project.security;

import it.polimi.db2.db2project.entities.Admin;
import it.polimi.db2.db2project.entities.User;
import it.polimi.db2.db2project.model.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class MyUserDetails implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        Admin admin = null;
        try {
            user = em.createNamedQuery("User.getByUsername", User.class).setParameter(1, username).getSingleResult();
            user.setLastLogin(new Date());
            em.persist(user);
        } catch (NoResultException e) {
            //e.printStackTrace();
        }
        try {
            admin = em.createNamedQuery("Admin.findByUsername", Admin.class).setParameter(1, username).getSingleResult();
        } catch (NoResultException e) {
            //e.printStackTrace();
        }

        if (user != null) {
            return org.springframework.security.core.userdetails.User//
                    .withUsername(username)//
                    .password(user.getPassword())//
                    .authorities(Role.ROLE_USER)
                    .accountExpired(false)//
                    .accountLocked(!user.isActive())//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        } else if (admin != null) {
            return org.springframework.security.core.userdetails.User//
                    .withUsername(username)//
                    .password(admin.getPassword())//
                    .authorities(Role.ROLE_ADMIN)
                    .accountExpired(false)//
                    .accountLocked(false)//
                    .credentialsExpired(false)//
                    .disabled(false)//
                    .build();
        } else {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }

}