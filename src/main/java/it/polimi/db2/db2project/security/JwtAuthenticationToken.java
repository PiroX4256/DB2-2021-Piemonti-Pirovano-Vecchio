package it.polimi.db2.db2project.security;

public class JwtAuthenticationToken {
    private String authToken;

    public JwtAuthenticationToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
