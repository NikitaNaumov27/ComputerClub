package ru.naumov.ComputerClub.models;

// Тариф: содержит данные о тарифных планах (название, цена за час, дополнительные услуги).

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Tariff")
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tariff_name")
    private String tariffName;

    @Min(0)
    @Max(2000)
    @Column(name = "price")
    private int price;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;

    public Tariff() {
    }

    public Tariff(String tariffName, int price, String description) {
        this.tariffName = tariffName;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}