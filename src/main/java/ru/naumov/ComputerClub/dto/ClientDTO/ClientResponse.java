package ru.naumov.ComputerClub.dto.ClientDTO;

import java.util.List;

public class ClientResponse {
    private List<ClientDTO> clients;

    public ClientResponse(List<ClientDTO> clients) {
        this.clients = clients;
    }

    public List<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(List<ClientDTO> clients) {
        this.clients = clients;
    }
}