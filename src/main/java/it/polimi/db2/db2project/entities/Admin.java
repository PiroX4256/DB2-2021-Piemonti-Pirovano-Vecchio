package it.polimi.db2.db2project.entities;

import javax.persistence.*;

@Entity
@NamedQueries({@NamedQuery(name = "Admin.findByUsername", query = "SELECT u FROM Admin u WHERE u.username = ?1")})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;

    protected Admin() {}

    public Admin(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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
}
