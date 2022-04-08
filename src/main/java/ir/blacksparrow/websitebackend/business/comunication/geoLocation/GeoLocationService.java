package ir.blacksparrow.websitebackend.business.comunication.geoLocation;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class GeoLocationService implements IGeoLocationService{

//    https://ipapi.co/8.8.8.8/json
//
//    {
//        "ip": "8.8.8.8",
//            "city": "Mountain View",
//            "region": "California",
//            "region_code": "CA",
//            "country": "US",
//            "country_name": "United States",
//            "continent_code": "NA",
//            "postal": "94035",
//            "latitude": 37.386,
//            "longitude": -122.0838,
//            "timezone": "America/Los_Angeles",
//            "utc_offset": "-0800",
//            "country_calling_code": "+1",
//            "currency": "USD",
//            "languages": "en-US,es-US,haw,fr",
//            "asn": "AS15169",
//            "org": "Google Inc."
//    }

    @Override
    public Locale getLocaleByIp(String ip){
        //todo: need implementation
        String lang= "en";
        String country= "US";
        return new Locale(lang, country);
    }
}
