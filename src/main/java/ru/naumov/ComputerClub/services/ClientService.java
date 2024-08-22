package ru.naumov.ComputerClub.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.repositories.ClientRepository;
import ru.naumov.ComputerClub.util.exceptions.ClientException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {this.clientRepository = clientRepository;}

    public List<Client> findAllClients() {
        log.info("In findAllClients - finding all clients");
        return clientRepository.findAll();
    }

    public Client findClientById(int id) {
        Optional<Client> client = clientRepository.findById(id);
        log.info("In findClientById - client: {}", client);
        return client.orElseThrow( () -> new ClientException("Client not found") );
    }

    @Transactional
    public void saveClient(Client client) {
        log.info("In saveClient - client: {}", client);
        clientRepository.save(client);
    }

    @Transactional
    public void updateClient(int id, Client client) {
        client.setId(id);
        log.info("In updateClient - client: {}", client);
        clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(int id) {
        log.info("In deleteClient - client with id: {}", id);
        clientRepository.deleteById(id);
    }
}