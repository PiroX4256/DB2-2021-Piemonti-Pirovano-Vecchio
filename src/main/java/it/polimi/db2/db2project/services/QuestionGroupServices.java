package it.polimi.db2.db2project.services;

import it.polimi.db2.db2project.entities.Question;
import it.polimi.db2.db2project.entities.QuestionGroup;
import it.polimi.db2.db2project.entities.Questionnaire;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class QuestionGroupServices {
    @PersistenceContext
    private EntityManager em;

    public void createQuestionGroup(String name, Long questionnaireId) {
        QuestionGroup questionGroup = new QuestionGroup(name);
        Questionnaire questionnaire = em.find(Questionnaire.class, questionnaireId);
        questionGroup.addQuestionnaire(questionnaire);
        questionnaire.addQuestionGroup(questionGroup);
        em.persist(questionGroup);
    }

    public List<Question> getQuestionsByQuestionGroup(Long questionGroupId) {
        return em.createNamedQuery("QuestionGroup.findQuestions", Question.class).setParameter(1, questionGroupId).getResultList();
    }
}
