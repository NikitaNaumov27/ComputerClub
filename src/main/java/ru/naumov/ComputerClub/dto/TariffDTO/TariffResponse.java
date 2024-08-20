package ru.naumov.ComputerClub.dto.TariffDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TariffResponse {

    private List<TariffDTO> tariffs;

    public TariffResponse(List<TariffDTO> tariffs) {
        this.tariffs = tariffs;
    }

}