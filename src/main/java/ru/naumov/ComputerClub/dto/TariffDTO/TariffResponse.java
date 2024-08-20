package ru.naumov.ComputerClub.dto.TariffDTO;

import java.util.List;

public class TariffResponse {

    private List<TariffDTO> tariffs;

    public TariffResponse(List<TariffDTO> tariffs) {
        this.tariffs = tariffs;
    }

    public List<TariffDTO> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<TariffDTO> tariffs) {
        this.tariffs = tariffs;
    }
}