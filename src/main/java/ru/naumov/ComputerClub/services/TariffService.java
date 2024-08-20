package ru.naumov.ComputerClub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.ComputerClub.models.Tariff;
import ru.naumov.ComputerClub.repositories.TariffRepositories;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TariffService {

    private final TariffRepositories tariffRepositories;

    @Autowired
    public TariffService(TariffRepositories tariffRepositories) {
        this.tariffRepositories = tariffRepositories;
    }

    public List<Tariff> findAllTariffs() {
        return tariffRepositories.findAll();
    }

    public Tariff findTariffById(int id) {
        Optional<Tariff> tariff = tariffRepositories.findById(id);
        return tariff.orElse(null);
    }

    @Transactional
    public void saveTariff(Tariff tariff) {
        tariffRepositories.save(tariff);
    }

    @Transactional
    public void updateTariff(int id, Tariff tariff) {
        tariff.setId(id);
        tariffRepositories.save(tariff);
    }

    @Transactional
    public void deleteTariff(int id) {
        tariffRepositories.deleteById(id);
    }
}