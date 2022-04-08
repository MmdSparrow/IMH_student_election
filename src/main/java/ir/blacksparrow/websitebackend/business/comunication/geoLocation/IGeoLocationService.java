package ir.blacksparrow.websitebackend.business.comunication.geoLocation;


import java.util.Locale;

public interface IGeoLocationService {

    Locale getLocaleByIp(String ip);
}
