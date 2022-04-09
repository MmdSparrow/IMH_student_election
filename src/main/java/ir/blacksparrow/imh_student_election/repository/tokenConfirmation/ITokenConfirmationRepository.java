package ir.blacksparrow.imh_student_election.repository.tokenConfirmation;

import ir.blacksparrow.imh_student_election.dataModel.TokenConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITokenConfirmationRepository  extends JpaRepository<TokenConfirmationEntity, Long> {
    @Query
    Optional<TokenConfirmationEntity> findByToken(String token);
}
