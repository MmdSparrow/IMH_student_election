package ir.blacksparrow.imh_student_election.business.sevice.registration;

import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.UserDto;
import ir.blacksparrow.imh_student_election.business.sevice.emailSender.EmailSenderService;
import ir.blacksparrow.imh_student_election.business.sevice.registration.validation.Validator;
import ir.blacksparrow.imh_student_election.business.sevice.tokenConfirmation.TokenConfirmationService;
import ir.blacksparrow.imh_student_election.business.sevice.user.UserService;
import ir.blacksparrow.imh_student_election.repository.tokenConfirmation.TokenConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistrationService implements IRegistrationService{

    private final UserService userService;
    private final TokenConfirmationRepository tokenConfirmationRepository;
    private final TokenConfirmationService tokenConfirmationService;
    private final EmailSenderService emailSenderService;
    private final Validator validator;

    @Override
    public String register(UserDto userDto) throws IllegalAccessException {
        boolean isValidEmail = validator.checkEmail(userDto.getEmailAddress());
        if(!isValidEmail){
            throw new IllegalAccessException("email not valid");
        }
        String token = userService.signupUser(userDto);

        emailSenderService.send(userDto, token);
        return token;
    }



//    @Transactional
    @Override
    public String confirmToken(String token){
        TokenConfirmationDtoChild tokenConfirmationDtoChild= tokenConfirmationRepository
                .findByToken(token)
                .orElseThrow(()->new IllegalStateException("token not found!"));

//        if(tokenConfirmationDtoChild.getConfirmTime()!=null){
//            throw new IllegalStateException("email already confirmed!");
//        }


        LocalDateTime expireTime = tokenConfirmationDtoChild.getExpireTime();

        if(expireTime.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
//        System.out.println(tokenConfirmationDtoChild.toString());
        tokenConfirmationService.setConfirmTime(tokenConfirmationDtoChild);
        try{
            System.out.println("wtf.....................................................");
            System.out.println(tokenConfirmationDtoChild.toString());
            System.out.println("wtf.....................................................");

            userService.enableUser(tokenConfirmationDtoChild.getUser().getEmailAddress());
        }catch (Exception ignored){

        }

        return "confirmed";
    }
}
