package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.MarketingQuestion;
import it.polimi.db2.db2project.entities.StatisticalQuestion;
import org.springframework.stereotype.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
@Stateless
public class StatisticalQuestionService {

        @PersistenceContext
        private EntityManager em;

        public List<StatisticalQuestion> findAll() {
            List<StatisticalQuestion> questionList = em.createNamedQuery("StatisticalQuestion.findAll", StatisticalQuestion.class).getResultList();
            return questionList;
        }
}
