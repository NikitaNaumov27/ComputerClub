package ru.naumov.ComputerClub.dto.SessionDTO;

import lombok.Getter;
import lombok.Setter;
import ru.naumov.ComputerClub.dto.TariffDTO.TariffDTO;

import java.util.List;

@Setter
@Getter
public class SessionResponse {

    private List<SessionDTO> sessions;

    public SessionResponse(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }

}