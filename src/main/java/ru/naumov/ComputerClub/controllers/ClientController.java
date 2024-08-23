package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.BaseResponse;
import ru.naumov.ComputerClub.dto.ClientDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.services.ClientService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController extends BaseMapper<Client, ClientDTO> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        super(modelMapper);
        this.clientService = clientService;
    }

    @GetMapping()
    public BaseResponse<ClientDTO> getAllClients() {
        return new BaseResponse<>(clientService.findAllClients().stream().map(a -> convertToDto(a, ClientDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{idClient}")
    public ClientDTO getClientById(@PathVariable int idClient) {
        return convertToDto(clientService.findClientById(idClient), ClientDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid ClientDTO clientDTO,
                                                BindingResult bindingResult) {
        clientService.checkException(bindingResult);
        clientService.saveClient(convertToEntity(clientDTO, Client.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{idClient}")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody @Valid ClientDTO clientDTO,
                                                   @PathVariable int idClient, BindingResult bindingResult) {
        clientService.checkException(bindingResult);
        clientService.updateClient(idClient, convertToEntity(clientDTO, Client.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable int idClient) {
        clientService.deleteClient(idClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}