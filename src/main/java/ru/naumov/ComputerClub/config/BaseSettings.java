package ru.naumov.ComputerClub.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseSettings {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}