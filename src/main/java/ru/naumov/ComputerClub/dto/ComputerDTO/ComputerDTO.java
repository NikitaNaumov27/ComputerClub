package ru.naumov.ComputerClub.dto.ComputerDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}