package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Компьютер: информация о компьютере (номер, статус занятости, характеристики).

@Entity
@Table(name = "Computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    @NotNull
    @Size(min = 2, max = 20)
    private String number;

    @Column(name = "status")
    @NotNull
    private boolean status;

    @Column(name = "specification")
    @NotNull
    @Size(min = 1, max = 50)
    private String specification;

    public Computer() {
    }

    public Computer(String number, boolean status, String specification) {
        this.number = number;
        this.status = status;
        this.specification = specification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }
}