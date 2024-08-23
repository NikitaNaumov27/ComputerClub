package ru.naumov.ComputerClub.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.naumov.ComputerClub.models.Tariff;
import ru.naumov.ComputerClub.repositories.TariffRepositories;
import ru.naumov.ComputerClub.util.exceptions.TariffException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class TariffService {

    private final TariffRepositories tariffRepositories;

    @Autowired
    public TariffService(TariffRepositories tariffRepositories) {
        this.tariffRepositories = tariffRepositories;
    }

    public List<Tariff> findAllTariffs() {
        log.info("In findAllTariffs - finding all tariffs");
        return tariffRepositories.findAll();
    }

    public Tariff findTariffById(int id) {
        Optional<Tariff> tariff = tariffRepositories.findById(id);
        log.info("In findTariffById - tariff: {}", tariff);
        return tariff.orElseThrow( () -> new TariffException("Tariff not found") );
    }

    @Transactional
    public void saveTariff(Tariff tariff) {
        log.info("In saveTariff - tariff: {}", tariff);
        tariffRepositories.save(tariff);
    }

    @Transactional
    public void updateTariff(int id, Tariff tariff) {
        tariff.setId(id);
        log.info("In updateTariff - tariff: {}", tariff);
        tariffRepositories.save(tariff);
    }

    @Transactional
    public void deleteTariff(int id) {
        log.info("In deleteTariff - tariff with id: {}", id);
        tariffRepositories.deleteById(id);
    }

    public void checkException(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ")
                        .append(error.getDefaultMessage()).append(";");
            }
            throw new TariffException(errorMsg.toString());
        }
    }
}