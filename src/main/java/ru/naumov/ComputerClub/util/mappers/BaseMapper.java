package ru.naumov.ComputerClub.util.mappers;

import org.modelmapper.ModelMapper;

public abstract class BaseMapper<T, D> {

    private final ModelMapper modelMapper;

    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public D convertToDto(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public T convertToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}