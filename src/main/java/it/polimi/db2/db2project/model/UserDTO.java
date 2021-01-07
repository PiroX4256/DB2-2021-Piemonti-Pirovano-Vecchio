package it.polimi.db2.db2project.model;


import it.polimi.db2.db2project.entities.User;

public class UserDTO {

    private String username;
    private String password;
    private String email;

    public User getUserFromDto(){
        User user = new User(username, email, password, true, 0);
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}