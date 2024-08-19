package ru.naumov.ComputerClub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.ComputerClub.models.Tariff;

@Repository
public interface TariffRepositories extends JpaRepository<Tariff, Integer> {

}