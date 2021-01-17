package it.polimi.db2.db2project.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "MarketingQuestion.findAll", query = "SELECT q FROM MarketingQuestion q "),})
                @NamedQuery(name = "MarketingQuestion.findById", query = "SELECT q FROM MarketingQuestion q WHERE q.id = ?1")
public class MarketingQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String questionContent;

    @OneToMany(mappedBy = "marketingQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MarketingAnswer> marketingAnswer;

    @ManyToOne
    @JoinColumn
    private Questionnaire questionnaire;

    protected MarketingQuestion() {}

    public MarketingQuestion(String questionContent, Questionnaire questionnaire) {
        this.questionContent = questionContent;
        this.questionnaire = questionnaire;
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

    public List<MarketingAnswer> getMarketingAnswer() {
        return marketingAnswer;
    }

    public void setMarketingAnswer(List<MarketingAnswer> marketingAnswer) {
        this.marketingAnswer = marketingAnswer;
    }

    public void addMarketingAnswer(MarketingAnswer marketingAnswer) {
        this.marketingAnswer.add(marketingAnswer);
    }
}
