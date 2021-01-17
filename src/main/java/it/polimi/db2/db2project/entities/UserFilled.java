package it.polimi.db2.db2project.entities;

import it.polimi.db2.db2project.model.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({@NamedQuery(name = "UserFilled.findByStatus", query = "SELECT u FROM UserFilled u WHERE u.status = ?1 AND u.questionnaire = ?2"),
                @NamedQuery(name = "UserFilled.findByQuestionnaireId", query = "SELECT u FROM UserFilled u WHERE u.questionnaire = ?1")})
public class UserFilled {

    @EmbeddedId
    UserFilledKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("questionnaireId")
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    private Status status;

    public UserFilled(Long userId, Long questionnaireId, User user, Questionnaire questionnaire, Status status) {
        this.id = new UserFilledKey(userId, questionnaireId);
        this.user = user;
        this.questionnaire = questionnaire;
        this.status = status;
    }

    public UserFilled() {

    }

    public UserFilledKey getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public Status getStatus() {
        return status;
    }
}
