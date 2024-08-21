package ru.naumov.ComputerClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.ComputerClub.models.Computer;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Integer> {
    List<Computer> findComputersByStatusIsTrue();
}