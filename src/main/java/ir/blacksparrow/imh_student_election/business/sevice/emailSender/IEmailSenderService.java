package ir.blacksparrow.imh_student_election.business.sevice.emailSender;

import ir.blacksparrow.imh_student_election.business.dto.UserDto;

public interface IEmailSenderService {
    void send(UserDto userDto, String token);
}
