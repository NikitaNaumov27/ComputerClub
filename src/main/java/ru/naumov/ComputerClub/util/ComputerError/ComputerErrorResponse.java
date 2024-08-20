package ru.naumov.ComputerClub.util.ComputerError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComputerErrorResponse {
    private String message;
    private long timestamp;

    public ComputerErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}