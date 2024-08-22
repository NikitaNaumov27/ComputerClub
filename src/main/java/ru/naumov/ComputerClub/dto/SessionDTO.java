package ru.naumov.ComputerClub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.models.Tariff;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessionDTO {

    @NotNull
    private int id;

    private Client client;

    private Computer computer;

    private Tariff tariff;

    @NotNull
    private LocalDateTime sessionStartTime;

    @NotNull
    private LocalDateTime sessionEndTime;

    @NotNull
    @Min(0)
    private int totalPrice;
}