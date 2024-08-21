package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Клиент: содержит информацию о клиенте (имя, возраст, время начала сессии, время окончания сессии, статус активности).

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "client_Name")
    @NotNull
    @Size(min = 2, max = 40)
    private String clientName;

    @Column(name = "age")
    @NotNull
    @Min(10)
    @Max(100)
    private int age;

    @Column(name = "is_active")
    @NotNull
    private boolean isActive;

    public Client(String clientName, int age, boolean isActive) {
        this.clientName = clientName;
        this.age = age;
        this.isActive = isActive;
    }
}