package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.BaseResponse;
import ru.naumov.ComputerClub.dto.ClientDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.services.ClientService;
import ru.naumov.ComputerClub.util.exceptions.ClientException;

import java.util.List;
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

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable int id) {
        return convertToDto(clientService.findClientById(id), ClientDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addClient(@RequestBody @Valid ClientDTO clientDTO,
                                                BindingResult bindingResult) {
        checkException(bindingResult);
        clientService.saveClient(convertToEntity(clientDTO, Client.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateClient(@RequestBody @Valid ClientDTO clientDTO,
                                                   @PathVariable int id, BindingResult bindingResult) {
        checkException(bindingResult);
        clientService.updateClient(id, convertToEntity(clientDTO, Client.class));
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
}