package ru.naumov.ComputerClub.dto.ComputerDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ComputerResponse {

    private List<ComputerDTO> computers;

    public ComputerResponse(List<ComputerDTO> computers) {
        this.computers = computers;
    }

}