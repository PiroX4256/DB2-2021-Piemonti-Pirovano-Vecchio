package it.polimi.db2.db2project.entities;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String answerContent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "answerUser", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name="question")
    private MarketingQuestion marketingQuestion;

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
