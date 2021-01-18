package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.OffensiveWords;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class OffensiveWordService {
    @PersistenceContext
    private EntityManager em;

    public List<OffensiveWords> getAllOffensiveWords() {
        return em.createNamedQuery("OffensiveWords.findAll", OffensiveWords.class).getResultList();
    }
}
