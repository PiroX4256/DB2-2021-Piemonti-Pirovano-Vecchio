package it.polimi.db2.db2project.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserFilledKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "questionnaire_id")
    Long questionnaireId;

    public UserFilledKey(Long userId, Long questionnaireId) {
        this.userId = userId;
        this.questionnaireId = questionnaireId;
    }

    public UserFilledKey() {

    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }
}
