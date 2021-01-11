package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "Questionnaire.findQuestionnaireByDate", query = "SELECT q FROM Questionnaire q WHERE q.date = ?1"),
        @NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q")})
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String productName;
    private String productImage;
    @Temporal(TemporalType.DATE)
    @Column
    private Date date;

    @ManyToMany(mappedBy = "questionnaireList")
    private List<User> questionnaireCompiledBy = new ArrayList<>();

    @OneToMany(mappedBy = "questionnaire")
    private List<MarketingQuestion> marketingQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "questionnaire")
    private List<StatisticalQuestion> statisticalQuestions = new ArrayList<>();

    protected Questionnaire() {}

    public Questionnaire(String productName, String productImage, Date date) {
        this.productName = productName;
        this.productImage = productImage;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public Date getDate() {
        return date;
    }

    public List<User> getQuestionnaireCompiledBy() {
        return questionnaireCompiledBy;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuestionnaireCompiledBy(List<User> questionnaireCompiledBy) {
        this.questionnaireCompiledBy = questionnaireCompiledBy;
    }

    public List<MarketingQuestion> getMarketingQuestions() {
        return marketingQuestions;
    }

    public void setMarketingQuestions(List<MarketingQuestion> marketingQuestions) {
        this.marketingQuestions = marketingQuestions;
    }

    public List<StatisticalQuestion> getStatisticalQuestions() {
        return statisticalQuestions;
    }

    public void setStatisticalQuestions(List<StatisticalQuestion> statisticalQuestions) {
        this.statisticalQuestions = statisticalQuestions;
    }

    public void addStatisticalQuestione(StatisticalQuestion statisticalQuestion) {
        this.statisticalQuestions.add(statisticalQuestion);
    }

    public void addMarketingQuestion(MarketingQuestion marketingQuestion) {
        this.marketingQuestions.add(marketingQuestion);
    }
}
