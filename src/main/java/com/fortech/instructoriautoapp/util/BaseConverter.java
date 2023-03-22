package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.BaseDto;
import com.fortech.instructoriautoapp.model.BaseEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<T extends BaseEntity<Long>, Dto extends BaseDto<Long>>
        implements Convertor<T, Dto> {
    public List<Dto> convertModelsToDtos(Collection<T> entity) {
        return entity.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }
}
