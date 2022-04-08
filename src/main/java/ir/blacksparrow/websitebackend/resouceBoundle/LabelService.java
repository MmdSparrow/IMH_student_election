package ir.blacksparrow.websitebackend.resouceBoundle;

import ir.blacksparrow.websitebackend.business.comunication.geoLocation.GeoLocationService;
import ir.blacksparrow.websitebackend.network.RequestIPService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

@AllArgsConstructor
@Service
public class LabelService implements ILabelService{
    private final RequestIPService requestIPService;
    private final GeoLocationService geoLocationService;

    @Override
    public String getMessageByKey(String labelKey, HttpServletRequest request){
        String clientIp= requestIPService.getClientIp(request);
        Locale locale= geoLocationService.getLocaleByIp(clientIp);
        ResourceBundle resourceBundle= ResourceBundle.getBundle("label_"+locale.getLanguage(),locale);
        return resourceBundle.getString(labelKey);
    }
}
