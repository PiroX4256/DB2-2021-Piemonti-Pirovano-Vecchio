package it.polimi.db2.db2project.model;

public class UserLeaderboardDTO {

    private final String username;
    private final int score;

    public UserLeaderboardDTO(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}
