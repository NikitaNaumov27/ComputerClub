package ru.naumov.ComputerClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Client;
import ru.naumov.ComputerClub.models.Session;
import ru.naumov.ComputerClub.repositories.SessionRepository;
import ru.naumov.ComputerClub.util.exceptions.SessionException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAllSessions() {
        return sessionRepository.findAll();
    }

    public Session findSessionById(int id) {
        Optional<Session> session = sessionRepository.findById(id);
        return session.orElseThrow( () -> new SessionException("Session not found") );
    }

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
    }

    @Transactional
    public void deleteSession(int id) {
        sessionRepository.deleteById(id);
    }

    public Session findSessionByClient(Client client) {
        return sessionRepository.findSessionByClient(client);
    }

    public void startSession(Session session) {
        session.setSessionStartTime(LocalDateTime.now());
        session.setSessionEndTime(LocalDateTime.now().plusHours(1));
        session.setTotalPrice(session.getTariff().getPrice());
    }

    public void endSession(Session session) {
        session.setSessionEndTime(LocalDateTime.now());
        int hours = (int) Duration.between(session.getSessionStartTime(), session.getSessionEndTime()).toHours();
        if (Duration.between(session.getSessionStartTime(), LocalDateTime.now()).toMinutes() > 0) hours++;
        session.setTotalPrice(session.getTariff().getPrice() * hours);
    }
}