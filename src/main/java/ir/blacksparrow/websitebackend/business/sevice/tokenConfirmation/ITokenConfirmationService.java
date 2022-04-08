package ir.blacksparrow.websitebackend.business.sevice.tokenConfirmation;

import ir.blacksparrow.websitebackend.business.dto.TokenConfirmationDto;
import ir.blacksparrow.websitebackend.business.dto.TokenConfirmationDtoChild;

import java.util.Optional;

public interface ITokenConfirmationService {
    Optional<TokenConfirmationDtoChild> insertAndUpdateTokenConfirmation(TokenConfirmationDto tokenConfirmationDto);
    Optional<TokenConfirmationDtoChild> setConfirmTime(TokenConfirmationDtoChild tokenConfirmationDtoChild);
}
