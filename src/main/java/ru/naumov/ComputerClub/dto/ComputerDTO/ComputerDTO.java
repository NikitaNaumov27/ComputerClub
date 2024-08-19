package ru.naumov.ComputerClub.dto.ComputerDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComputerDTO {

    @NotNull
    private int id;

    @NotNull
    private boolean status;

    @NotNull
    @Size(min = 1, max = 50)
    private String specifications;

    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
