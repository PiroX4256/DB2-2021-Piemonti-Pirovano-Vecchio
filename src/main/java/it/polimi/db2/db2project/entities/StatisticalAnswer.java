package it.polimi.db2.db2project.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class StatisticalAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @OneToOne
    private User user;
    @NotNull
    @ManyToOne
    private StatisticalQuestion statisticalQuestion;
    @NotNull
    private String answer;

    public StatisticalAnswer(User user, StatisticalQuestion statisticalQuestion, String answer) {
        this.user = user;
        this.statisticalQuestion = statisticalQuestion;
        this.answer = answer;
    }

    public StatisticalAnswer() {
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

    public StatisticalQuestion getMarketingQuestion() {
        return statisticalQuestion;
    }

    public void setMarketingQuestion(StatisticalQuestion statisticalQuestion) {
        this.statisticalQuestion = statisticalQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
