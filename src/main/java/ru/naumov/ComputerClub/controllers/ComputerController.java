package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.ComputerDTO.ComputerDTO;
import ru.naumov.ComputerClub.dto.ComputerDTO.ComputerResponse;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.services.ComputerService;
import ru.naumov.ComputerClub.util.ClientError.ClientException;
import ru.naumov.ComputerClub.util.ComputerError.ComputerErrorResponse;
import ru.naumov.ComputerClub.util.ComputerError.ComputerException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/computers")
public class ComputerController {

    private final ComputerService computerService;
    private final ModelMapper modelMapper;

    @Autowired
    public ComputerController(ComputerService computerService, ModelMapper modelMapper) {
        this.computerService = computerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ComputerResponse getAllComputers() {
        return new ComputerResponse(computerService.findAllComputers().stream().map(this::convertToComputerDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public Computer getComputerById(@PathVariable int id) {
        return computerService.findComputerById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addComputer(@Valid @RequestBody ComputerDTO computerDTO,
                                                  BindingResult bindingResult) {
        checkException(bindingResult);
        computerService.saveComputer(convertToComputer(computerDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateComputer(@RequestBody @Valid ComputerDTO computerDTO,
                                                     @PathVariable int id, BindingResult bindingResult) {
        checkException(bindingResult);
        computerService.updateComputer(id, convertToComputer(computerDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComputer(@PathVariable int id){
        computerService.deleteComputer(id);
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
            throw new ComputerException(errorMsg.toString());
        }
    }

    private ComputerDTO convertToComputerDTO(Computer computer) {
        return modelMapper.map(computer, ComputerDTO.class);
    }

    private Computer convertToComputer(ComputerDTO computerDTO) {
        return modelMapper.map(computerDTO, Computer.class);
    }

    @ExceptionHandler
    public ResponseEntity<ComputerErrorResponse> handleException(final Exception ex) {
        ComputerErrorResponse response = new ComputerErrorResponse(
                ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}