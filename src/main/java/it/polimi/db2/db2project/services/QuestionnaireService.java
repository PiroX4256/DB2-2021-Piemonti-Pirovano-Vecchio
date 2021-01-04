package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.Questionnaire;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class QuestionnaireService {
    @PersistenceContext(unitName = "QuestionnaireEJB")
    private EntityManager em;

    public Long createQuestionnaire(String productName, String productImage, Date date) {
        Questionnaire q = new Questionnaire(productName, productImage, date);
        em.persist(q);
        em.flush();
        return q.getId();
    }

    public List<Questionnaire> findAllQuestionnaires() {
        return em.createNamedQuery("Questionnaire.findAll", Questionnaire.class).getResultList();
    }

    public List<Questionnaire> findByDate(Date date) {
        return em.createNamedQuery("Questionnaire.findQuestionnaireByDate", Questionnaire.class).getResultList();
    }
}
