package com.fortech.instructoriautoapp.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionMessages {
    ENTITY_SHOULD_BE_NON_NULL("Entity should be non-null."),
    ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST("Entity with given id does not exist."),
    ENTITY_WITH_GIVEN_ID_ALREADY_EXISTS("Entity with given id already exists."),
    REPOSITORY_NOT_FOUND("The given repository was not found: ");

    public final String errorMessage;
}
