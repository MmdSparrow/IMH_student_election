package ir.blacksparrow.imh_student_election.business.sevice.registration;

import ir.blacksparrow.imh_student_election.business.dto.UserDto;

public interface IRegistrationService {
    String register(UserDto request) throws IllegalAccessException;
    String confirmToken(String token);
}
