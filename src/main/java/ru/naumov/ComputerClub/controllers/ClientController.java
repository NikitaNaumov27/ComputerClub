package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.ClientDTO.ClientDTO;
import ru.naumov.ComputerClub.dto.ClientDTO.ClientResponse;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.services.ClientService;
import ru.naumov.ComputerClub.util.ClientError.ClientErrorResponse;
import ru.naumov.ComputerClub.util.ClientError.ClientException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ClientResponse getAllClients() {
        return new ClientResponse(clientService.findAllClients().stream().map(this::convertToClientDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientService.findClientById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid ClientDTO clientDTO,
                                                BindingResult bindingResult) {
        checkException(bindingResult);
        clientService.saveClient(convertToClient(clientDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody @Valid ClientDTO clientDTO,
                                                   @PathVariable int id, BindingResult bindingResult) {
        checkException(bindingResult);
        clientService.updateClient(id, convertToClient(clientDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

    private ClientDTO convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }

    private Client convertToClient(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    @ExceptionHandler
    public ResponseEntity<ClientErrorResponse> handleException(final Exception ex) {
        ClientErrorResponse response = new ClientErrorResponse(
                ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}