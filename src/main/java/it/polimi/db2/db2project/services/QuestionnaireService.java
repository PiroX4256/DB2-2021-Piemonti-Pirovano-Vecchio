package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.*;
import it.polimi.db2.db2project.model.ProductDTO;
import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Stateless
public class QuestionnaireService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Questionnaire createQuestionnaire(ProductDTO productDTO) {
        Questionnaire q = new Questionnaire(productDTO.getProductName(), productDTO.getProductImage(), productDTO.getDate());
        em.persist(q);
        em.flush();
        return q;
    }

    @Transactional
    public MarketingAnswer createMarketingAnswer(User user, MarketingQuestion question, String answerContent) {
        MarketingAnswer answer = new MarketingAnswer(user, question, answerContent);
        em.persist(answer);
        em.flush();
        return answer;
    }

    @Transactional
    public StatisticalAnswer createStatisticalAnswer(User user, StatisticalQuestion question, int answerContent) {
        StatisticalAnswer answer = new StatisticalAnswer(user, question, answerContent);
        em.persist(answer);
        em.flush();
        return answer;
    }

    public List<Questionnaire> findAllQuestionnaires() {
        return em.createNamedQuery("Questionnaire.findAll", Questionnaire.class).getResultList();
    }

    public Questionnaire findByDate(Date date) {
        return em.createNamedQuery("Questionnaire.findQuestionnaireByDate", Questionnaire.class).setParameter(1, date).getSingleResult();
    }

    public Questionnaire findById(Long id) {
        return em.createNamedQuery("Questionnaire.findQuestionnaireById", Questionnaire.class).setParameter(1, id).getSingleResult();
    }
}
