package ir.blacksparrow.websitebackend.business.sevice.tokenConfirmation;

import ir.blacksparrow.websitebackend.business.dto.TokenConfirmationDto;
import ir.blacksparrow.websitebackend.business.dto.TokenConfirmationDtoChild;
import ir.blacksparrow.websitebackend.business.dto.UserDto;
import ir.blacksparrow.websitebackend.repository.tokenConfirmation.TokenConfirmationRepository;
import ir.blacksparrow.websitebackend.repository.user.UserRepository;
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
        tokenConfirmationDtoChild.setConfirmTime(LocalDateTime.now());
        return tokenConfirmationRepository.insertAndUpdate(tokenConfirmationDtoChild);
    }
}
