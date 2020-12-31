package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "Questionnaire.findQuestionnaireOfTheDay", query = "SELECT q FROM Questionnaire q WHERE q.date = ?1")})
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String productName;
    private String productImage;
    private Date date;

    @ManyToMany(mappedBy = "questionnaireList", fetch = FetchType.LAZY)
    private List<User> questionnaireCompiledBy = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "QuestionnaireQuestionGroup", schema = "db2", joinColumns = @JoinColumn(name = "questionnaireId"), inverseJoinColumns = @JoinColumn(name = "questionGroupId"))
    private List<QuestionGroup> questionGroups = new ArrayList<>();

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
}
