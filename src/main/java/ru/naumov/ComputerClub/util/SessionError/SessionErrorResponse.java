package ru.naumov.ComputerClub.util.SessionError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionErrorResponse {

    private String message;
    private long timestamp;

    public SessionErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
