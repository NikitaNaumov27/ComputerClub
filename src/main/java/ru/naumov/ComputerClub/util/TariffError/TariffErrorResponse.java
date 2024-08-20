package ru.naumov.ComputerClub.util.TariffError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TariffErrorResponse {
    private String message;
    private long timestamp;

    public TariffErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}