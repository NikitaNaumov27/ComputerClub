package ru.naumov.ComputerClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Session;
import ru.naumov.ComputerClub.repositories.SessionRepository;

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
        return session.orElse(null);
    }

    @Transactional
    public void saveSession(Session session) {
        sessionRepository.save(session);
    }

    @Transactional
    public void deleteSession(int id) {
        sessionRepository.deleteById(id);
    }

    public void startSession(Session session) {
        session.setSessionStartTime(LocalDateTime.now());
    }

    public void endSession(Session session) {
        session.setSessionEndTime(LocalDateTime.now());
        double hours = Duration.between(session.getSessionStartTime(), session.getSessionEndTime()).toHours();
        session.setTotalPrice(session.getTariff().getPrice() * hours);
    }
}