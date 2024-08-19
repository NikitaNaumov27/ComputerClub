package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

// Клиент: содержит информацию о клиенте (имя, возраст, время начала сессии, время окончания сессии, статус активности).
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

    @Column(name = "session_start_time")
    @NotNull
    private LocalDateTime sessionStartTime;

    @Column(name = "session_end_time")
    @NotNull
    private LocalDateTime sessionEndTime;

    @Column(name = "is_active")
    @NotNull
    private boolean isActive;

    public Client() {
    }

    public Client(String clientName, int age, LocalDateTime sessionStartTime, LocalDateTime sessionEndTime, boolean isActive) {
        this.clientName = clientName;
        this.age = age;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public @NotNull LocalDateTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(@NotNull LocalDateTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public @NotNull LocalDateTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(@NotNull LocalDateTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}