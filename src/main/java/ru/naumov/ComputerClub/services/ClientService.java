package ru.naumov.ComputerClub.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    public void checkException(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }
            throw new ClientException(errorMsg.toString());
        }
    }
}