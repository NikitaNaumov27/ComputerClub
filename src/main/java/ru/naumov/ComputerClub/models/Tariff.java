package ru.naumov.ComputerClub.models;

// Тариф: содержит данные о тарифных планах (название, цена за час, дополнительные услуги).

public class Tariff {

    private int id;
    private String tariffName;
    private int price;
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