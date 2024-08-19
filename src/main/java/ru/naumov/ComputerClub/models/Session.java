package ru.naumov.ComputerClub.models;

// Сессия: отражает информацию о сессии клиента (клиент, компьютер, время начала, время окончания, итоговая стоимость).

import java.time.LocalDate;

public class Session {
    private int id;
    private Client client;
    private Computer computer;
    private LocalDate sessionStartTime;
    private LocalDate sessionEndTime;
    private int totalPrice;

    public Session() {
    }

    public Session(Client client, Computer computer, LocalDate sessionStartTime, LocalDate sessionEndTime, int totalPrice) {
        this.client = client;
        this.computer = computer;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public LocalDate getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDate sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalDate getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalDate sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}