package ir.blacksparrow.imh_student_election.business.sevice.tokenConfirmation;

import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDto;
import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.UserDto;
import ir.blacksparrow.imh_student_election.repository.tokenConfirmation.TokenConfirmationRepository;
import ir.blacksparrow.imh_student_election.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TokenConfirmationService implements ITokenConfirmationService {

    private final TokenConfirmationRepository tokenConfirmationRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<TokenConfirmationDtoChild> insertAndUpdateTokenConfirmation(TokenConfirmationDto tokenConfirmationDto) {
        UserDto userDto = userRepository.findByUsername(tokenConfirmationDto.getUsername()).orElse(null);
        TokenConfirmationDtoChild tokenConfirmationDtoChild = new TokenConfirmationDtoChild(
                tokenConfirmationDto.getId(),
                tokenConfirmationDto.getToken(),
                tokenConfirmationDto.getCreateTime(),
                tokenConfirmationDto.getExpireTime(),
                tokenConfirmationDto.getConfirmTime(),
                userDto
        );
        return tokenConfirmationRepository.insertAndUpdate(tokenConfirmationDtoChild);
    }

    @Override
    public Optional<TokenConfirmationDtoChild> setConfirmTime(TokenConfirmationDtoChild tokenConfirmationDtoChild){
//        System.out.println("test...........................");
        tokenConfirmationDtoChild.setConfirmTime(LocalDateTime.now());
        return tokenConfirmationRepository.insertAndUpdate(tokenConfirmationDtoChild);
    }
}
