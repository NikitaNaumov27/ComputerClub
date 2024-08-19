package ru.naumov.ComputerClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    @Transactional
    public void addClient(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void updateClient(int id, Client client) {
        client.setId(id);
        clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

   /* public Client getClientByName(String name) {
        return clientRepository.findByName(name);
    }
    */
}
