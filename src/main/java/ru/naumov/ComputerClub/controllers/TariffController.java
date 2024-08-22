package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.BaseResponse;
import ru.naumov.ComputerClub.dto.TariffDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Tariff;
import ru.naumov.ComputerClub.services.TariffService;
import ru.naumov.ComputerClub.util.exceptions.TariffException;

import java.util.List;
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

    @GetMapping("/{id}")
    public TariffDTO getTariffById(@PathVariable int id) {
        return convertToDto(tariffService.findTariffById(id), TariffDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addTariff(@RequestBody @Valid TariffDTO tariffDTO,
                                                    BindingResult bindingResult) {
        checkException(bindingResult);
        tariffService.saveTariff(convertToEntity(tariffDTO, Tariff.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTariff(@RequestBody @Valid TariffDTO tariffDTO,
                                                       @PathVariable int id, BindingResult bindingResult) {
        checkException(bindingResult);
        tariffService.updateTariff(id, convertToEntity(tariffDTO, Tariff.class));
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
}