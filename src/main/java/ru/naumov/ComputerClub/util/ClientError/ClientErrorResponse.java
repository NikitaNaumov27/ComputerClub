package ru.naumov.ComputerClub.util.ClientError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientErrorResponse {
    private String message;
    private long timestamp;

    public ClientErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}