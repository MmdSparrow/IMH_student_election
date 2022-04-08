package ir.blacksparrow.websitebackend.repository.tokenConfirmation;

import ir.blacksparrow.websitebackend.business.dto.TokenConfirmationDtoChild;
import ir.blacksparrow.websitebackend.business.dto.UserDto;
import ir.blacksparrow.websitebackend.dataModel.TokenConfirmationEntity;
import ir.blacksparrow.websitebackend.dataModel.UserEntity;
import ir.blacksparrow.websitebackend.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenConfirmationRepository extends ParentRepository {
    private final ITokenConfirmationRepository tokenConfirmationRepository;


    public TokenConfirmationRepository(ModelMapper modelMapper, ITokenConfirmationRepository tokenConfirmationRepository) {
        super(modelMapper);
        this.tokenConfirmationRepository = tokenConfirmationRepository;
    }

    public Optional<TokenConfirmationDtoChild> insertAndUpdate(TokenConfirmationDtoChild tokenConfirmationDto) {
        TokenConfirmationEntity tokenConfirmationEntity = getModelMapper().map(tokenConfirmationDto, TokenConfirmationEntity.class);
        tokenConfirmationEntity = tokenConfirmationRepository.save(tokenConfirmationEntity);
        return Optional.of(getModelMapper().map(tokenConfirmationEntity, TokenConfirmationDtoChild.class));
    }

    public Optional<TokenConfirmationDtoChild> findByToken(String token){
        return Optional.of(getModelMapper().map(tokenConfirmationRepository.findByToken(token).orElse(null), TokenConfirmationDtoChild.class));
    }
}
