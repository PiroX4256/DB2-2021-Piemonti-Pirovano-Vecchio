package it.polimi.db2.db2project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class MarketingAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @OneToOne
    private User user;
    @NotNull
    @ManyToOne
    @JsonBackReference
    private MarketingQuestion marketingQuestion;
    @Column(nullable = false)
    private String answer;

    public MarketingAnswer(User user, MarketingQuestion marketingQuestion, String answer) {
        this.user = user;
        this.marketingQuestion = marketingQuestion;
        this.answer = answer;
    }

    public MarketingAnswer() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MarketingQuestion getMarketingQuestion() {
        return marketingQuestion;
    }

    public void setMarketingQuestion(MarketingQuestion marketingQuestion) {
        this.marketingQuestion = marketingQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
