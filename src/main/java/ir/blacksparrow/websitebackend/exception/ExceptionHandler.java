package ir.blacksparrow.websitebackend.exception;

import ir.blacksparrow.websitebackend.business.dto.ResponseDto;
import ir.blacksparrow.websitebackend.resouceBoundle.LabelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@AllArgsConstructor
@ControllerAdvice
public class ExceptionHandler {
    private final LabelService labelService;

    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto> handleRequestException(CustomException e) {
        ExceptionTemplate exceptionTemplate = new ExceptionTemplate(
                HttpStatus.BAD_REQUEST,
                labelService.getMessageByKey(e.getLabelKey(), e.getRequest())
        );
        return new ResponseEntity<>(new ResponseDto(false, exceptionTemplate.message(), null), exceptionTemplate.httpStatus());
    }
}
