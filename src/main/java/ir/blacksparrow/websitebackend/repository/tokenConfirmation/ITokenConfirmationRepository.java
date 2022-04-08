package ir.blacksparrow.websitebackend.repository.tokenConfirmation;

import ir.blacksparrow.websitebackend.dataModel.TokenConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITokenConfirmationRepository  extends JpaRepository<TokenConfirmationEntity, Long> {
    @Query
    Optional<TokenConfirmationEntity> findByToken(String token);
}
