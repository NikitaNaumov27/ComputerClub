package ru.naumov.ComputerClub.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BaseErrorResponse {
    private String message;
    private long timestamp;
}
