package ru.naumov.ComputerClub.dto.ClientDTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}