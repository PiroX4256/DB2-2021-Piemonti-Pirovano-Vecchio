package it.polimi.db2.db2project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OffensiveWords {
    @Id
    private String word;

    protected OffensiveWords(){}

    public OffensiveWords(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
