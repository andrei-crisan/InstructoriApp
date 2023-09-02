package com.fortech.instructoriautoapp.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionMessages {
    ENTITY_SHOULD_BE_NON_NULL("Entity should be non-null."),
    INSTRUCTOR_WITH_THE_GIVEN_IDENTIFIERS_ALREADY_EXISTS("Instructor with the given name, surname and driving school already exists."),
    INSTRUCTOR_WITH_GIVEN_ID_DOES_NOT_EXIST("Instructor with the given id does not exist."),
    DRIVING_SCHOOL_WITH_GIVEN_IDENTIFIERS_ALREADY_EXISTS("Driving school with the given name and address already exists."),
    DRIVING_SCHOOL_WITH_GIVEN_ID_DOES_NOT_EXIST("Driving school with the given id does not exist."),
    REVIEW_WITH_GIVEN_ID_DOES_NOT_EXIST("Review with the given id does not exist."),
    REPOSITORY_NOT_FOUND("The given repository was not found: "),
    ROLE_NOT_FOUND("The role is not found.");

    public final String errorMessage;
}
