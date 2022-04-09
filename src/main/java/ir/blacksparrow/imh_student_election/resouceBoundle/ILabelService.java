package ir.blacksparrow.imh_student_election.resouceBoundle;

import javax.servlet.http.HttpServletRequest;

public interface ILabelService {
    String getMessageByKey(String labelKey, HttpServletRequest request);
}
