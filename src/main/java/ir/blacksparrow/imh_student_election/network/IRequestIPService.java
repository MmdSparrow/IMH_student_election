package ir.blacksparrow.imh_student_election.network;

import javax.servlet.http.HttpServletRequest;

public interface IRequestIPService {
    String getClientIp(HttpServletRequest request);
}
