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
    private int answer;

    public StatisticalAnswer(User user, StatisticalQuestion statisticalQuestion, int answer) {
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

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
