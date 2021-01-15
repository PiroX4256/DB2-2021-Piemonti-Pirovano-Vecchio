package it.polimi.db2.db2project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "OffensiveWords.findAll", query = "SELECT w FROM OffensiveWords w")
public class OffensiveWords {
    @Id
    private String word;

    protected OffensiveWords(){}

    public OffensiveWords(String word) {
        this.word = word.toUpperCase();
    }

    public String getWord() {
        return word;
    }
}
