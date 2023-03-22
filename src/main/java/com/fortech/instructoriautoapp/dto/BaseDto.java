package com.fortech.instructoriautoapp.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto <ID> implements Serializable {
    protected ID id;
}
