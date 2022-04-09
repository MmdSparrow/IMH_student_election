package ir.blacksparrow.imh_student_election.exception;

import org.springframework.http.HttpStatus;

public record ExceptionTemplate(HttpStatus httpStatus, String message) {
}
