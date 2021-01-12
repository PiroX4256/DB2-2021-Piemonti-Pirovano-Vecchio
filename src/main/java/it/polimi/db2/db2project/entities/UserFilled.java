package it.polimi.db2.db2project.entities;

import it.polimi.db2.db2project.model.Status;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "userFilled")
public class UserFilled implements Serializable {

    private Status status;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<User> users;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<Questionnaire> questionnaires;
}
