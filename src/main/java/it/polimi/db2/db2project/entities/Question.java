package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String questionContent;

    @ManyToMany(mappedBy = "questionList")
    private List<QuestionGroup> questionGroupList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Answer> answersForThisQuestion;

    protected Question() {}

    public Question(String questionContent) {
        this.questionContent = questionContent;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionContent() {
        return questionContent;
    }
}
