package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.TariffDTO.TariffDTO;
import ru.naumov.ComputerClub.dto.TariffDTO.TariffResponse;
import ru.naumov.ComputerClub.models.Tariff;
import ru.naumov.ComputerClub.services.TariffService;
import ru.naumov.ComputerClub.util.ClientError.ClientException;
import ru.naumov.ComputerClub.util.TariffError.TariffErrorResponse;
import ru.naumov.ComputerClub.util.TariffError.TariffException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tariffs")
public class TariffController {

    private final TariffService tariffService;
    private final ModelMapper modelMapper;

    @Autowired
    public TariffController(TariffService tariffService, ModelMapper modelMapper) {
        this.tariffService = tariffService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public TariffResponse getAllTariffs() {
        return new TariffResponse(tariffService.findAllTariffs().stream().map(this::convertToTariffDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Tariff getTariffById(@PathVariable int id) {
        return tariffService.findTariffById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addTariff(@RequestBody @Valid TariffDTO tariffDTO,
                                                    BindingResult bindingResult) {
        checkException(bindingResult);
        tariffService.saveTariff(convertToTariff(tariffDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody @Valid TariffDTO tariffDTO,
                                                       @PathVariable int id, BindingResult bindingResult) {
        checkException(bindingResult);
        tariffService.updateTariff(id, convertToTariff(tariffDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTariff(@PathVariable int id) {
        tariffService.deleteTariff(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

    private TariffDTO convertToTariffDTO(Tariff tariff) {
        return modelMapper.map(tariff, TariffDTO.class);
    }

    private Tariff convertToTariff(TariffDTO tariffDTO) {
        return modelMapper.map(tariffDTO, Tariff.class);
    }

    @ExceptionHandler
    public ResponseEntity<TariffErrorResponse> handleException(final Exception ex) {
        TariffErrorResponse response = new TariffErrorResponse(
                ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}