package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Тариф: содержит данные о тарифных планах (название, цена за час, дополнительные услуги).
@Getter
@Setter
@NoArgsConstructor
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

    public Tariff(String tariffName, int price, String description) {
        this.tariffName = tariffName;
        this.price = price;
        this.description = description;
    }
}