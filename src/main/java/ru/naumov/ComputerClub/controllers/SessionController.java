package ru.naumov.ComputerClub.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.BaseResponse;
import ru.naumov.ComputerClub.dto.SessionDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.models.Session;
import ru.naumov.ComputerClub.models.Tariff;
import ru.naumov.ComputerClub.services.ClientService;
import ru.naumov.ComputerClub.services.ComputerService;
import ru.naumov.ComputerClub.services.SessionService;
import ru.naumov.ComputerClub.services.TariffService;
import ru.naumov.ComputerClub.util.exceptions.SessionException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessions")
public class SessionController extends BaseMapper<Session, SessionDTO> {

    private final SessionService sessionService;
    private final ClientService clientService;
    private final ComputerService computerService;
    private final TariffService tariffService;

    @Autowired
    public SessionController(SessionService sessionService, ClientService clientService,
                             ComputerService computerService, TariffService tariffService,
                             ModelMapper modelMapper) {
        super(modelMapper);
        this.sessionService = sessionService;
        this.clientService = clientService;
        this.computerService = computerService;
        this.tariffService = tariffService;
    }

    @GetMapping()
    public BaseResponse<SessionDTO> getAllSessions() {
        return new BaseResponse<>(sessionService.findAllSessions().stream().map(a -> convertToDto(a, SessionDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{idSession}")
    public SessionDTO getSessionsByClient(@PathVariable int idSession) {
        return convertToDto(sessionService.findSessionById(idSession), SessionDTO.class);
    }

    @GetMapping("/actives")
    public BaseResponse<SessionDTO> getAllActivesSessions() {
        return new BaseResponse<>(sessionService.findAllActiveSessions().stream().map(a -> convertToDto(a, SessionDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/start/{idClient}/{idComputer}/{idTariff}")
    public ResponseEntity<HttpStatus> startSession(@PathVariable int idClient, @PathVariable int idComputer,
                                                   @PathVariable int idTariff) {
        Session session = new Session();
        Client client = clientService.findClientById(idClient);
        session.setClient(client);
        Computer computer = computerService.findComputerById(idComputer);
        if (!computer.isStatus()) throw new SessionException("Этот компьютер уже занят :с");
        computer.setStatus(false);
        session.setComputer(computer);
        Tariff tariff = tariffService.findTariffById(idTariff);
        session.setTariff(tariff);
        sessionService.startSession(session);
        sessionService.saveSession(session);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/end/{idSession}")
    public ResponseEntity<HttpStatus> endSession(@PathVariable int idSession) {
        Session session = sessionService.findSessionById(idSession);
        sessionService.endSession(session);
        session.getComputer().setStatus(true);
        sessionService.saveSession(session);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idSession}")
    public ResponseEntity<HttpStatus> deleteSession(@PathVariable int idSession) {
        sessionService.deleteSession(idSession);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}