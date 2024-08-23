package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.BaseResponse;
import ru.naumov.ComputerClub.dto.TariffDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Tariff;
import ru.naumov.ComputerClub.services.TariffService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tariffs")
public class TariffController extends BaseMapper<Tariff, TariffDTO> {

    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService, ModelMapper modelMapper) {
        super(modelMapper);
        this.tariffService = tariffService;
    }

    @GetMapping()
    public BaseResponse<TariffDTO> getAllTariffs() {
        return new BaseResponse<>(tariffService.findAllTariffs().stream().map(a -> convertToDto(a, TariffDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{idTariff}")
    public TariffDTO getTariffById(@PathVariable int idTariff) {
        return convertToDto(tariffService.findTariffById(idTariff), TariffDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addTariff(@RequestBody @Valid TariffDTO tariffDTO,
                                                    BindingResult bindingResult) {
        tariffService.checkException(bindingResult);
        tariffService.saveTariff(convertToEntity(tariffDTO, Tariff.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{idTariff}")
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody @Valid TariffDTO tariffDTO,
                                                       @PathVariable int idTariff, BindingResult bindingResult) {
        tariffService.checkException(bindingResult);
        tariffService.updateTariff(idTariff, convertToEntity(tariffDTO, Tariff.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idTariff}")
    public ResponseEntity<HttpStatus> deleteTariff(@PathVariable int idTariff) {
        tariffService.deleteTariff(idTariff);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}