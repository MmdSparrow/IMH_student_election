package ir.blacksparrow.imh_student_election.business.sevice.user;

import ir.blacksparrow.imh_student_election.business.dto.UserDto;

import java.rmi.ServerException;
import java.util.Optional;

public interface IUserService {
    UserDto getUserByUsername(String username);
    String signupUser(UserDto user);
    Optional<UserDto> enableUser(String emailAddress) throws ServerException;
}
