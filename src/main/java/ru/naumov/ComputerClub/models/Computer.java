package ru.naumov.ComputerClub.models;

//Компьютер: информация о компьютере (номер, статус занятости, характеристики).

public class Computer {

    private int id;
    private boolean status;
    private String specificationsComp;

    public Computer() {
    }

    public Computer(boolean status, String specificationsComp) {
        this.status = status;
        this.specificationsComp = specificationsComp;
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

    public String getSpecificationsComp() {
        return specificationsComp;
    }

    public void setSpecificationsComp(String specificationsComp) {
        this.specificationsComp = specificationsComp;
    }
}