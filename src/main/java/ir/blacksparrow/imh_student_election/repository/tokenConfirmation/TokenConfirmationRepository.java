package ir.blacksparrow.imh_student_election.repository.tokenConfirmation;

import ir.blacksparrow.imh_student_election.business.dto.PermissionDto;
import ir.blacksparrow.imh_student_election.business.dto.RoleDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.TokenConfirmationDtoChild;
import ir.blacksparrow.imh_student_election.dataModel.PermissionEntity;
import ir.blacksparrow.imh_student_election.dataModel.TokenConfirmationEntity;
import ir.blacksparrow.imh_student_election.repository.ParentRepository;
import ir.blacksparrow.imh_student_election.repository.role.IRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TokenConfirmationRepository extends ParentRepository {
    private final ITokenConfirmationRepository tokenConfirmationRepository;
    private final IRoleRepository roleRepository;


    public TokenConfirmationRepository(ModelMapper modelMapper, ITokenConfirmationRepository tokenConfirmationRepository, IRoleRepository roleRepository) {
        super(modelMapper);
        this.tokenConfirmationRepository = tokenConfirmationRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<TokenConfirmationDtoChild> insertAndUpdate(TokenConfirmationDtoChild tokenConfirmationDto) {
        TokenConfirmationEntity tokenConfirmationEntity = getModelMapper().map(tokenConfirmationDto, TokenConfirmationEntity.class);
        tokenConfirmationEntity = tokenConfirmationRepository.save(tokenConfirmationEntity);
        return Optional.of(getModelMapper().map(tokenConfirmationEntity, TokenConfirmationDtoChild.class));
    }

    public Optional<TokenConfirmationDtoChild> findByToken(String token){
        TokenConfirmationEntity tokenConfirmationEntity=tokenConfirmationRepository.findByToken(token).orElse(null);
        assert tokenConfirmationEntity != null;
        System.out.println("final.........................................");
        System.out.println(tokenConfirmationEntity.toString());
        TokenConfirmationDtoChild tokenConfirmationDtoChild=getModelMapper().map(tokenConfirmationEntity, TokenConfirmationDtoChild.class);

        RoleDtoChild roleDtoChild= new RoleDtoChild();
        roleDtoChild.setTitle(tokenConfirmationEntity.getUser().getRole().getTitle());
        List<PermissionEntity> permissionDtoList = new ArrayList<>(tokenConfirmationEntity.getUser().getRole().getPermissions());

        roleDtoChild.setPermissions(mapList(permissionDtoList,PermissionDto.class));
        tokenConfirmationDtoChild.getUser().setRoleDtoChild(roleDtoChild);
        System.out.println(tokenConfirmationDtoChild.toString());
        System.out.println("final.........................................");

        return Optional.of(tokenConfirmationDtoChild);
        //todo:bug..................
    }
}
