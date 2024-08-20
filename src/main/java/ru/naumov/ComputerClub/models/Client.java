package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

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

    @Column(name = "is_active")
    @NotNull
    private boolean isActive;

    @OneToMany(mappedBy = "client")
    private List<Session> sessions;

    public Client() {
    }

    public Client(String clientName, int age, boolean isActive,
                  List<Session> sessions) {
        this.clientName = clientName;
        this.age = age;
        this.isActive = isActive;
        this.sessions = sessions;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}