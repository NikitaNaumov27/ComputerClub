package ru.naumov.ComputerClub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private List<T> response;
}
