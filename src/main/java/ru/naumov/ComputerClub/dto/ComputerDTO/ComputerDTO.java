package ru.naumov.ComputerClub.dto.ComputerDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComputerDTO {

    @NotNull
    private int id;

    @NotNull
    @Size(min = 2, max = 20)
    private String number;

    @NotNull
    private boolean status;

    @NotNull
    @Size(min = 1, max = 50)
    private String specification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}