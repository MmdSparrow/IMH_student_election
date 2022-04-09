package ir.blacksparrow.imh_student_election.repository.role;

import ir.blacksparrow.imh_student_election.business.dto.RoleDto;
import ir.blacksparrow.imh_student_election.dataModel.RoleEntity;
import ir.blacksparrow.imh_student_election.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class RoleRepository extends ParentRepository {
    private final IRoleRepository roleRepository;


    public RoleRepository(ModelMapper modelMapper, IRoleRepository roleRepository) {
        super(modelMapper);
        this.roleRepository = roleRepository;

    }

    public List<RoleDto> findAll() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        return mapList(roleEntityList, RoleDto.class);
    }

    public List<RoleDto> findAll(int offset, int size) {
        List<RoleEntity> roleEntityList = roleRepository.findAll(offset, size);
        return mapList(roleEntityList, RoleDto.class);
    }

    public Optional<RoleDto> getById(Long id) {
        RoleEntity roleEntity = roleRepository.getById(id);
        return Optional.of(getModelMapper().map(roleEntity, RoleDto.class));
    }

    public Optional<RoleDto> insertAndUpdate(RoleDto roleDto) {
        RoleEntity roleEntity = getModelMapper().map(roleDto, RoleEntity.class);
        roleEntity = roleRepository.save(roleEntity);
        return Optional.of(getModelMapper().map(roleEntity, RoleDto.class));
    }

    public List<RoleDto> insertAndUpdateAll(List<RoleDto> roleDtoList) {
        List<RoleEntity> roleEntityList = mapList(roleDtoList, RoleEntity.class);
        roleEntityList = roleRepository.saveAll(roleEntityList);
        return mapList(roleEntityList, RoleDto.class);
    }
}
