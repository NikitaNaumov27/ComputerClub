package ru.naumov.ComputerClub.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

import java.util.List;
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

    @GetMapping("/{id}")
    public SessionDTO getSessionsByClient(@PathVariable int id) {
        return convertToDto(sessionService.findSessionById(id), SessionDTO.class);
    }

    @PostMapping("/start/{idClient}/{idComputer}/{idTariff}")
    public ResponseEntity<HttpStatus> startSession(@PathVariable int idClient, @PathVariable int idComputer,
                                                   @PathVariable int idTariff) {
        Session session = new Session();
        Client client = clientService.findClientById(idClient);
        if (client == null) throw new SessionException("Клиента с таким id не существует (Пармаетры запроса:/{idClient}/{idComputer}/{idTariff})");
        if (sessionService.findSessionByClient(client)!=null)throw new SessionException("Сессия для клиента с таким id уже существует");
        session.setClient(client);
        Computer computer = computerService.findComputerById(idComputer);
        if (computer == null) throw new SessionException("Компьютера с таким id не существует (Пармаетры запроса:/{idClient}/{idComputer}/{idTariff})");
        if (!computer.isStatus()) throw new SessionException("Этот компьютер уже занят :с");
        computer.setStatus(false);
        session.setComputer(computer);
        Tariff tariff = tariffService.findTariffById(idTariff);
        if (tariff == null) throw new SessionException("Тарифа с таким id не существует (Пармаетры запроса:/{idClient}/{idComputer}/{idTariff})");
        session.setTariff(tariff);
        sessionService.startSession(session);
        sessionService.saveSession(session);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/end/{id}")
    public ResponseEntity<HttpStatus> endSession(@PathVariable int id) {
        Session session = sessionService.findSessionByClient(clientService.findClientById(id));
        sessionService.endSession(session);
        session.getComputer().setStatus(true);
        sessionService.saveSession(session);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSession(@PathVariable int id) {
        sessionService.deleteSession(id);
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
            throw new SessionException(errorMsg.toString());
        }
    }
}