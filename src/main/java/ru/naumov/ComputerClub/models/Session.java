package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Сессия: отражает информацию о сессии клиента (клиент, компьютер, время начала, время окончания, итоговая стоимость).

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "session_start_time")
    @NotNull
    private LocalDateTime sessionStartTime;

    @Column(name = "session_end_time")
    @NotNull
    private LocalDateTime sessionEndTime;

    @Column(name = "total_price")
    @NotNull
    @Min(0)
    private int totalPrice;

    public Session(Client client, Computer computer, Tariff tariff,
                   LocalDateTime sessionStartTime, LocalDateTime sessionEndTime, int totalPrice) {
        this.client = client;
        this.computer = computer;
        this.tariff = tariff;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.totalPrice = totalPrice;
    }
}