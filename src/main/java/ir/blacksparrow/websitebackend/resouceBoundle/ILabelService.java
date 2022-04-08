package ir.blacksparrow.websitebackend.resouceBoundle;

import javax.servlet.http.HttpServletRequest;

public interface ILabelService {
    String getMessageByKey(String labelKey, HttpServletRequest request);
}
