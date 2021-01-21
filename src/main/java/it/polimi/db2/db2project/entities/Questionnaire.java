package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "Questionnaire.findQuestionnaireByDate", query = "SELECT q FROM Questionnaire q WHERE DATE(q.date) = DATE(?1)"),
        @NamedQuery(name = "Questionnaire.findAll", query = "SELECT q FROM Questionnaire q"),
        @NamedQuery(name = "Questionnaire.findQuestionnaireById", query = "SELECT q FROM Questionnaire q WHERE q.id = ?1")})
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String productName;
    @Column(nullable = false)
    private String productImage;
    @Temporal(TemporalType.DATE)
    @Column(unique = true, nullable = false)
    private Date date;

    @OneToMany(mappedBy = "questionnaire", orphanRemoval = true)

    private List<UserFilled> questionnaireCompiledBy = new ArrayList<>();

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MarketingQuestion> marketingQuestions = new ArrayList<>();

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

    public List<UserFilled> getQuestionnaireCompiledBy() {
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

    public void setQuestionnaireCompiledBy(List<UserFilled> questionnaireCompiledBy) {
        this.questionnaireCompiledBy = questionnaireCompiledBy;
    }

    public List<MarketingQuestion> getMarketingQuestions() {
        return marketingQuestions;
    }

    public void setMarketingQuestions(List<MarketingQuestion> marketingQuestions) {
        this.marketingQuestions = marketingQuestions;
    }

    public void addMarketingQuestion(MarketingQuestion marketingQuestion) {
        this.marketingQuestions.add(marketingQuestion);
    }
}
