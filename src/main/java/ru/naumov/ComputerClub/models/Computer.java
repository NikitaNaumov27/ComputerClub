package ru.naumov.ComputerClub.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

//Компьютер: информация о компьютере (номер, статус занятости, характеристики).

@Getter
@Setter
@ToString
@NoArgsConstructor
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

    public Computer(String number, boolean status, String specification) {
        this.number = number;
        this.status = status;
        this.specification = specification;
    }
}