package ru.naumov.ComputerClub.dto.SessionDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.models.Tariff;

import java.time.LocalDate;

@Getter
@Setter
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
}