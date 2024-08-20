package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.SessionDTO.SessionDTO;
import ru.naumov.ComputerClub.dto.SessionDTO.SessionResponse;
import ru.naumov.ComputerClub.models.Session;
import ru.naumov.ComputerClub.services.SessionService;
import ru.naumov.ComputerClub.util.SessionError.SessionErrorResponse;
import ru.naumov.ComputerClub.util.SessionError.SessionException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;
    private final ModelMapper modelMapper;

    @Autowired
    public SessionController(final SessionService sessionService, final ModelMapper modelMapper) {
        this.sessionService = sessionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public SessionResponse getAllSessions() {
        return new SessionResponse(sessionService.findAllSessions().stream().map(this::convertToSessionDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Session getSessionsByClient(@PathVariable int id) {
        return sessionService.findSessionById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addSession(@RequestBody @Valid SessionDTO sessionDTO,
                                                 BindingResult bindingResult) {
        checkException(bindingResult);
        sessionService.saveSession(convertToSession(sessionDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }










/*
    @GetMapping("/active")
    public List<SessionDTO> getActiveSessions() {
        return sessionService.getActiveSessions();
    }

    @GetMapping("/by-client/{clientId}")
    public List<SessionDTO> getSessionsByClient(@PathVariable int clientId) {
        return sessionService.getSessionsByClient(clientId);
    }

    @GetMapping("/by-computer/{computerId}")
    public List<SessionDTO> getSessionsByComputer(@PathVariable int computerId) {
        return sessionService.getSessionsByComputer(computerId);
    }


    @PostMapping("/start")
    public ResponseEntity<SessionDTO> startSession(@RequestBody SessionDTO sessionDTO) {
        return new ResponseEntity<>(sessionService.startSession(sessionDTO), HttpStatus.CREATED);
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<SessionDTO> endSession(@PathVariable int id) {
        return new ResponseEntity<>(sessionService.endSession(id), HttpStatus.OK);
    }

 */

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

    private SessionDTO convertToSessionDTO(Session session) {
        return modelMapper.map(session, SessionDTO.class);
    }

    private Session convertToSession(SessionDTO sessionDTO) {
        return modelMapper.map(sessionDTO, Session.class);
    }

    @ExceptionHandler
    public ResponseEntity<SessionErrorResponse> handleException(final Exception ex) {
        SessionErrorResponse response = new SessionErrorResponse(
                ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
