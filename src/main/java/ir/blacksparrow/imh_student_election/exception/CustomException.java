package ir.blacksparrow.imh_student_election.exception;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;

@Getter
public class CustomException extends RuntimeException {
    private HttpServletRequest request;
    private String labelKey;

    public CustomException(HttpServletRequest request, String labelKey) {
        super("");
        this.request = request;
        this.labelKey = labelKey;
    }
}
