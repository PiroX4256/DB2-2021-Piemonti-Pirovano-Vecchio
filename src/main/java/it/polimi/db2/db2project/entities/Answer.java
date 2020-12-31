package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String answerContent;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "answerUser", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="question")
    private Question question;

    public Answer() {}

    public Answer(String answerContent, User user) {
        this.answerContent = answerContent;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public User getUser() {
        return user;
    }
}
