package ir.blacksparrow.websitebackend.business.sevice.registration;

import ir.blacksparrow.websitebackend.business.dto.UserDto;

public interface IRegistrationService {
    String register(UserDto request) throws IllegalAccessException;
    String confirmToken(String token);
}
