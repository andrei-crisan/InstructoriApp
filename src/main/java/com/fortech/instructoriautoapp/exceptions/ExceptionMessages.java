package com.fortech.instructoriautoapp.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionMessages {
    ENTITY_SHOULD_BE_NON_NULL("Entity should be non-null."),
    INSTRUCTOR_WITH_THE_GIVEN_IDENTIFIERS_ALREADY_EXISTS("Instructor with the given name, surname and driving school already exists."),
    DRIVING_SCHOOL_WITH_GIVEN_IDENTIFIERS_ALREADY_EXISTS("Driving school with the given name and address already exists."),
    ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST("Entity with given id does not exist."),
    ENTITY_WITH_GIVEN_ID_ALREADY_EXISTS("Entity with given id already exists."),
    REPOSITORY_NOT_FOUND("The given repository was not found: ");

    public final String errorMessage;
}
