package ir.blacksparrow.websitebackend.network;

import javax.servlet.http.HttpServletRequest;

public interface IRequestIPService {
    String getClientIp(HttpServletRequest request);
}
