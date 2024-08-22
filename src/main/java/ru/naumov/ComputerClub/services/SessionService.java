package ru.naumov.ComputerClub.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Session;
import ru.naumov.ComputerClub.repositories.SessionRepository;
import ru.naumov.ComputerClub.util.exceptions.SessionException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAllSessions() {
        log.info("In findAllSessions - finding all sessions");
        return sessionRepository.findAll();
    }

    public Session findSessionById(int id) {
        Optional<Session> session = sessionRepository.findById(id);
        log.info("In findSessionById - session: {}", session);
        return session.orElseThrow( () -> new SessionException("Session not found") );
    }

    public List<Session> findAllActiveSessions() {
        log.info("In findAllActiveSessions - finding all active sessions");
        return sessionRepository.findAll().stream().filter(session -> LocalDateTime.now()
                .isBefore(session.getSessionEndTime())).collect(Collectors.toList());
    }

    @Transactional
    public void saveSession(Session session) {
        log.info("In saveSession - session: {}", session);
        sessionRepository.save(session);
    }

    @Transactional
    public void deleteSession(int id) {
        log.info("In deleteSession - session with id: {}", id);
        sessionRepository.deleteById(id);
    }

    public void startSession(Session session) {
        session.setSessionStartTime(LocalDateTime.now());
        session.setSessionEndTime(LocalDateTime.now().plusHours(1));
        session.setTotalPrice(session.getTariff().getPrice());
        log.info("In startSession - session: {}", session);
    }

    public void endSession(Session session) {
        session.setSessionEndTime(LocalDateTime.now());
        int hours = (int) Duration.between(session.getSessionStartTime(), session.getSessionEndTime()).toHours();
        if (Duration.between(session.getSessionStartTime(), LocalDateTime.now()).toMinutes() > 0) hours++;
        session.setTotalPrice(session.getTariff().getPrice() * hours);
        log.info("In endSession - session: {}", session);
    }
}