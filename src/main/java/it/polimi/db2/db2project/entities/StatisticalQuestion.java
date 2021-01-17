package it.polimi.db2.db2project.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "StatisticalQuestion.findAll", query = "SELECT q FROM StatisticalQuestion q")})
public class StatisticalQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String questionContent;

    @OneToMany(mappedBy = "statisticalQuestion", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<StatisticalAnswer> statisticalAnswers;

    @ManyToOne
    @JoinColumn
    private Questionnaire questionnaire;

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

    public List<StatisticalAnswer> getStatisticalAnswers() {
        return statisticalAnswers;
    }

    public void setStatisticalAnswers(List<StatisticalAnswer> statisticalAnswers) {
        this.statisticalAnswers = statisticalAnswers;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public void addStatisticalAnswer(StatisticalAnswer statisticalAnswer) {
        this.statisticalAnswers.add(statisticalAnswer);
    }
}
