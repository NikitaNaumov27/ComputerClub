package ru.naumov.ComputerClub.dto.SessionDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.models.Tariff;

import java.time.LocalDate;

public class SessionDTO {

    @NotNull
    private int id;

    private Client client;

    private Computer computer;

    private Tariff tariff;

    @NotNull
    private LocalDate sessionStartTime;

    @NotNull
    private LocalDate sessionEndTime;

    @NotNull
    @Min(0)
    private int totalPrice;

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

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
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