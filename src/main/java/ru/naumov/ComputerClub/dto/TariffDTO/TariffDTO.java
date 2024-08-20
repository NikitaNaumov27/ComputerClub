package ru.naumov.ComputerClub.dto.TariffDTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TariffDTO {

    @NotNull
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    private String tariffName;

    @Min(0)
    @Max(2000)
    private int price;

    @NotNull
    @Size(min = 1, max = 50)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}