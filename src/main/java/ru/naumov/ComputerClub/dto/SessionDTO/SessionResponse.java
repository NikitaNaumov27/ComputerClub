package ru.naumov.ComputerClub.dto.SessionDTO;

import ru.naumov.ComputerClub.dto.TariffDTO.TariffDTO;

import java.util.List;

public class SessionResponse {

    private List<SessionDTO> sessions;

    public SessionResponse(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }

    public List<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }
}