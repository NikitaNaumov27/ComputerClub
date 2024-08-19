package ru.naumov.ComputerClub.dto.ClientDTO;

import jakarta.validation.constraints.*;

import java.time.LocalTime;

public class ClientDTO {

    @NotNull
    @Size(min = 2, max = 40)
    private String clientName;

    @NotNull
    @Min(10)
    @Max(100)
    private int age;

    @NotNull
    //@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}",message = "123")
    private LocalTime sessionStartTime;

    @NotNull
    //@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}",message = "123")
    private LocalTime sessionEndTime;

    @NotNull
    private boolean isActive;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}