package ru.naumov.ComputerClub.dto.ClientDTO;

import jakarta.validation.constraints.*;

public class ClientDTO {

    @NotNull
    private int id;

    @NotNull
    @Size(min = 2, max = 40)
    private String clientName;

    @NotNull
    @Min(10)
    @Max(100)
    private int age;

    @NotNull
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}