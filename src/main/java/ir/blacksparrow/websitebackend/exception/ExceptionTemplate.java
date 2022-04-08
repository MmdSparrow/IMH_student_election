package ir.blacksparrow.websitebackend.exception;

import org.springframework.http.HttpStatus;

public record ExceptionTemplate(HttpStatus httpStatus, String message) {
}
