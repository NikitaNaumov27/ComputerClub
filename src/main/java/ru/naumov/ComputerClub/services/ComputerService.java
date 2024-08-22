package ru.naumov.ComputerClub.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.repositories.ComputerRepository;
import ru.naumov.ComputerClub.util.exceptions.ComputerException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ComputerService {

    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> findAllComputers() {
        log.info("In findAllComputers - finding all computers");
        return computerRepository.findAll();
    }

    public Computer findComputerById(int id) {
        Optional<Computer> computer = computerRepository.findById(id);
        log.info("In findComputerById - computer: {}", computer);
        return computer.orElseThrow( () -> new ComputerException("Computer not found") );
    }

    @Transactional
    public void saveComputer(Computer computer) {
        log.info("In saveComputer - saving computer: {}", computer);
        computerRepository.save(computer);
    }

    @Transactional
    public void updateComputer(int id, Computer computer) {
        computer.setId(id);
        log.info("In updateComputer - computer: {}", computer);
        computerRepository.save(computer);
    }

    @Transactional
    public void deleteComputer(int id) {
        log.info("In deleteComputer - computer with id: {}", id);
        computerRepository.deleteById(id);
    }

    public List<Computer> findComputersByStatusIsTrue(){
        log.info("In findComputersByStatusIsTrue - finding all computers by status true");
        return computerRepository.findComputersByStatusIsTrue();
    }
}