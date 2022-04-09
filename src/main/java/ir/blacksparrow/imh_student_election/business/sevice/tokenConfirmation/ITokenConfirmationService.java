package ir.blacksparrow.imh_student_election.business.sevice.tokenConfirmation;

import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDto;
import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDtoChild;

import java.util.Optional;

public interface ITokenConfirmationService {
    Optional<TokenConfirmationDtoChild> insertAndUpdateTokenConfirmation(TokenConfirmationDto tokenConfirmationDto);
    Optional<TokenConfirmationDtoChild> setConfirmTime(TokenConfirmationDtoChild tokenConfirmationDtoChild);
}
