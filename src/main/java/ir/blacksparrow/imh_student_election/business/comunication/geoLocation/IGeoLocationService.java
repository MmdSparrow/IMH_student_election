package ir.blacksparrow.imh_student_election.business.comunication.geoLocation;


import java.util.Locale;

public interface IGeoLocationService {

    Locale getLocaleByIp(String ip);
}
