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
import ru.naumov.ComputerClub.dto.ComputerDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.services.ComputerService;
import ru.naumov.ComputerClub.util.exceptions.ComputerException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/computers")
public class ComputerController extends BaseMapper<Computer, ComputerDTO> {

    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService, ModelMapper modelMapper) {
        super(modelMapper);
        this.computerService = computerService;
    }

    @GetMapping()
    public BaseResponse<ComputerDTO> getAllComputers() {
        return new BaseResponse<>(computerService.findAllComputers().stream().map(a -> convertToDto(a, ComputerDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ComputerDTO getComputerById(@PathVariable int id) {
        return convertToDto(computerService.findComputerById(id), ComputerDTO.class);
    }

    @GetMapping("/free")
    public BaseResponse<ComputerDTO> getAllFreeComputers() {
        return new BaseResponse<>(computerService.findComputersByStatusIsTrue().stream().map(a -> convertToDto(a, ComputerDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addComputer(@Valid @RequestBody ComputerDTO computerDTO,
                                                  BindingResult bindingResult) {
        checkException(bindingResult);
        computerService.saveComputer(convertToEntity(computerDTO, Computer.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateComputer(@RequestBody @Valid ComputerDTO computerDTO,
                                                     @PathVariable int id, BindingResult bindingResult) {
        checkException(bindingResult);
        computerService.updateComputer(id, convertToEntity(computerDTO, Computer.class));
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
}