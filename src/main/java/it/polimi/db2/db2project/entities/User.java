package it.polimi.db2.db2project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User", schema = "db2")
@NamedQueries({@NamedQuery(name = "User.checkCredentials", query = "SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2"),
@NamedQuery(name = "User.getByUsername", query = "SELECT u FROM User u WHERE u.username = ?1")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    private String username;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private Date lastLogin;
    @JsonIgnore
    private boolean active;
    private int score;

    @OneToMany(mappedBy = "user")
    private List<UserFilled> userFilled = new ArrayList<>();

    public User() {}

    public User(String username, String email, String password, boolean active, int score) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setQuestionnaireList(List<UserFilled> userFilled) {
        this.userFilled = userFilled;
    }

    public void setId(long userId) {
    }
}
