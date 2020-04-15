package by.chekun.dto.mapper;


import by.chekun.dto.AbstractDto;
import by.chekun.model.AbstractEntity;

import java.util.List;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D d);

    D toDto(E e);

    List<D> toDtoList(List<E> eList);
}