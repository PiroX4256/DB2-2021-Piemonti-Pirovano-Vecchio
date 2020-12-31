package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuestionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "questionGroups", fetch = FetchType.EAGER)
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
}
