package com.fortech.instructoriautoapp.util;

import com.fortech.instructoriautoapp.dto.BaseDto;
import com.fortech.instructoriautoapp.model.BaseEntity;

public interface Convertor<Model extends BaseEntity<Long>, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);
    Dto convertModelToDto(Model model);
}
