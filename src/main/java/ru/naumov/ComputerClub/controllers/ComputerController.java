package ru.naumov.ComputerClub.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumov.ComputerClub.dto.BaseResponse;
import ru.naumov.ComputerClub.dto.ComputerDTO;
import ru.naumov.ComputerClub.util.mappers.BaseMapper;
import ru.naumov.ComputerClub.models.Computer;
import ru.naumov.ComputerClub.services.ComputerService;

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

    @GetMapping("/{idComputer}")
    public ComputerDTO getComputerById(@PathVariable int idComputer) {
        return convertToDto(computerService.findComputerById(idComputer), ComputerDTO.class);
    }

    @GetMapping("/free")
    public BaseResponse<ComputerDTO> getAllFreeComputers() {
        return new BaseResponse<>(computerService.findComputersByStatusIsTrue().stream().map(a -> convertToDto(a, ComputerDTO.class))
                .collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addComputer(@Valid @RequestBody ComputerDTO computerDTO,
                                                  BindingResult bindingResult) {
        computerService.checkException(bindingResult);
        computerService.saveComputer(convertToEntity(computerDTO, Computer.class));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{idComputer}")
    public ResponseEntity<HttpStatus> updateComputer(@RequestBody @Valid ComputerDTO computerDTO,
                                                     @PathVariable int idComputer, BindingResult bindingResult) {
        computerService.checkException(bindingResult);
        computerService.updateComputer(idComputer, convertToEntity(computerDTO, Computer.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idComputer}")
    public ResponseEntity<HttpStatus> deleteComputer(@PathVariable int idComputer){
        computerService.deleteComputer(idComputer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}