package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class StatisticalQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String questionContent;

    @OneToMany(mappedBy = "statisticalQuestion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatisticalAnswer> statisticalAnswers;

    protected StatisticalQuestion() {}

    public StatisticalQuestion(String questionContent) {
        this.questionContent = questionContent;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public List<StatisticalAnswer> getstatisticalAnswer() {
        return statisticalAnswers;
    }

    public void setMarketingAnswer(List<StatisticalAnswer> statisticalAnswers) {
        this.statisticalAnswers = statisticalAnswers;
    }
}
