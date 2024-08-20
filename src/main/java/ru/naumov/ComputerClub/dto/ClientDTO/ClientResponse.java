package ru.naumov.ComputerClub.dto.ClientDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ClientResponse {
    private List<ClientDTO> clients;

    public ClientResponse(List<ClientDTO> clients) {
        this.clients = clients;
    }

}