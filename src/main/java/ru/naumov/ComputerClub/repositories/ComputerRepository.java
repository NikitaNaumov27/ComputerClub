package ru.naumov.ComputerClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.ComputerClub.models.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Integer> {

}