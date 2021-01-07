package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@NamedQueries({@NamedQuery(name = "QuestionGroup.findQuestions", query = "SELECT q FROM Question q JOIN QuestionAssociate qa ON q.id = qa.questionId WHERE qa.questionGroupId = ?1")})
public class QuestionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "questionGroups")
    private List<Questionnaire> questionnaireList = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "QuestionAssociate", schema = "db2", joinColumns = @JoinColumn(name = "questionGroupId"), inverseJoinColumns = @JoinColumn(name = "questionId"))
    private List<Question> questionList = new ArrayList<>();

    protected QuestionGroup() {}

    public QuestionGroup(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void addQuestionnaire(Questionnaire questionnaire) {
        getQuestionnaireList().add(questionnaire);
    }
}
