package ir.blacksparrow.websitebackend.business.sevice.emailSender;

import ir.blacksparrow.websitebackend.business.dto.UserDto;

public interface IEmailSenderService {
    void send(UserDto userDto, String token);
}
