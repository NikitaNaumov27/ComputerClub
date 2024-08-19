package ru.naumov.ComputerClub.dto.ComputerDTO;

import java.util.List;

public class ComputerResponse {

    private List<ComputerDTO> computers;

    public ComputerResponse(List<ComputerDTO> computers) {
        this.computers = computers;
    }

    public List<ComputerDTO> getComputers() {
        return computers;
    }

    public void setComputers(List<ComputerDTO> computers) {
        this.computers = computers;
    }
}
