package ru.naumov.ComputerClub.util.ClientError;

public class ClientException extends RuntimeException {
    public ClientException(String msg) {
        super(msg);
    }
}