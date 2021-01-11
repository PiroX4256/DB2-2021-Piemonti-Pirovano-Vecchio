package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.MarketingQuestion;
import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
@Stateless
public class MarketingQuestionService {

    @PersistenceContext
    private EntityManager em;

    public MarketingQuestion findById(Long id) {
        return em.createNamedQuery("MarketingQuestion.findById", MarketingQuestion.class).setParameter(1, id).getSingleResult();
    }
}