package ru.naumov.ComputerClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.ComputerClub.models.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {

}