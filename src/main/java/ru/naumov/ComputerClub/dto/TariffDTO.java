package ru.naumov.ComputerClub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}