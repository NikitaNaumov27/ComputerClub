package ru.naumov.ComputerClub.models;

import java.time.LocalDate;

// Клиент: содержит информацию о клиенте (имя, возраст, время начала сессии, время окончания сессии, статус активности).

public class Client {

    private int id;
    private String name;
    private int age;
    private LocalDate sessionStartTime;
    private LocalDate sessionEndTime;
    private boolean isActive;

    public Client() {
    }

    public Client(String name, int age, LocalDate sessionStartTime, LocalDate sessionEndTime, boolean isActive) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalDate sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalDate getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalDate sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}