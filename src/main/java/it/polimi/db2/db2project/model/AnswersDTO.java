package it.polimi.db2.db2project.model;

import java.io.Serializable;
import java.util.List;

public class AnswersDTO {

    private final List<String> answers;
    private final boolean isCancelled;

    public AnswersDTO(List<String> answers, boolean isCancelled) {
        this.answers = answers;
        this.isCancelled = isCancelled;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public boolean isCancelled() {
        return isCancelled;
    }
}
