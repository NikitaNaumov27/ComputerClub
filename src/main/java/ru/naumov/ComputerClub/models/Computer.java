package ru.naumov.ComputerClub.models;

//Компьютер: информация о компьютере (номер, статус занятости, характеристики).

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Computer")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    @NotNull
    private boolean status;

    @Column(name = "specification")
    @NotNull
    @Size(min = 1, max = 50)
    private String specifications;

    public Computer() {
    }

    public Computer(boolean status, String specifications) {
        this.status = status;
        this.specifications = this.specifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specificationsComp) {
        this.specifications = specifications;
    }
}