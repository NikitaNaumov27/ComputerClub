package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.time.LocalTime;

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
    private LocalTime sessionStartTime;

    @Column(name = "session_end_time")
    @NotNull
    //@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}",message = "123")
    private LocalTime sessionEndTime;

    @Column(name = "is_active")
    @NotNull
    //@Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}",message = "123")
    private boolean isActive;

    public Client() {
    }

    public Client(String clientName, int age, LocalTime sessionStartTime, LocalTime sessionEndTime, boolean isActive) {
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

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}