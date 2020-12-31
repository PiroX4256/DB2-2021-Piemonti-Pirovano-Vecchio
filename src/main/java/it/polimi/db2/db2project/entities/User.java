package it.polimi.db2.db2project.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User", schema = "db2")
@NamedQuery(name = "User.checkCredentials", query = "SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private Date lastLogin;
    private boolean active;
    private int score;

    @ManyToMany
    @JoinTable(name = "UserFilled", schema = "db2", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "questionnaireId"))
    private List<Questionnaire> questionnaireList = new ArrayList<>();

    protected User() {}

    public User(String username, String email, String password, Date lastLogin, boolean active, int score) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.lastLogin = lastLogin;
        this.active = active;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public boolean isActive() {
        return active;
    }

    public int getScore() {
        return score;
    }
}
