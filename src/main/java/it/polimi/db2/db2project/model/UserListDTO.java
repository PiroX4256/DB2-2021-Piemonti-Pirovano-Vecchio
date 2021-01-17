package it.polimi.db2.db2project.model;

import java.util.List;

public class UserListDTO {

    private List<String> submittedUsers;
    private List<String> cancelledUsers;

    public UserListDTO(List<String> submittedUsers, List<String> cancelledUsers) {
        this.submittedUsers = submittedUsers;
        this.cancelledUsers = cancelledUsers;
    }

    public List<String> getSubmittedUsers() {
        return submittedUsers;
    }

    public List<String> getCancelledUsers() {
        return cancelledUsers;
    }
}


