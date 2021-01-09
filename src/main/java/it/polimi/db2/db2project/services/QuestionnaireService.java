package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.Questionnaire;
import it.polimi.db2.db2project.model.QuestionnaireDTO;
import org.hibernate.type.CalendarTimeType;
import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Stateless
public class QuestionnaireService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Questionnaire createQuestionnaire(QuestionnaireDTO questionnaireDTO) {
        Questionnaire q = new Questionnaire(questionnaireDTO.getProductName(), questionnaireDTO.getProductImage(), questionnaireDTO.getDate());
        em.persist(q);
        em.flush();
        return q;
    }

    public List<Questionnaire> findAllQuestionnaires() {
        return em.createNamedQuery("Questionnaire.findAll", Questionnaire.class).getResultList();
    }

    public Questionnaire findByDate(Date date) {
        return em.createNamedQuery("Questionnaire.findQuestionnaireByDate", Questionnaire.class).setParameter(1, date, TemporalType.DATE).getSingleResult();
    }

    public Questionnaire findById(Long id) {
        return em.createNamedQuery("Questionnaire.findQuestionnaireById", Questionnaire.class).setParameter(1, id).getSingleResult();
    }
}
