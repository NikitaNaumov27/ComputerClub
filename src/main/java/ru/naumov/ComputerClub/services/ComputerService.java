package ru.naumov.ComputerClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.repositories.ComputerRepository;
import ru.naumov.ComputerClub.util.exceptions.ClientException;
import ru.naumov.ComputerClub.util.exceptions.ComputerException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ComputerService {

    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> findAllComputers() {
        return computerRepository.findAll();
    }

    public Computer findComputerById(int id) {
        Optional<Computer> computer = computerRepository.findById(id);
        return computer.orElseThrow( () -> new ComputerException("Computer not found") );
    }

    @Transactional
    public void saveComputer(Computer computer) {
        computerRepository.save(computer);
    }

    @Transactional
    public void updateComputer(int id, Computer computer) {
        computer.setId(id);
        computerRepository.save(computer);
    }

    @Transactional
    public void deleteComputer(int id) {
        computerRepository.deleteById(id);
    }

    public List<Computer> findComputersByStatusIsTrue(){
        return computerRepository.findComputersByStatusIsTrue();
    }
}