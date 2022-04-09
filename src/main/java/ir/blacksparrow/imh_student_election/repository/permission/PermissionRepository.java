package ir.blacksparrow.imh_student_election.repository.permission;

import ir.blacksparrow.imh_student_election.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.PermissionDto;
import ir.blacksparrow.imh_student_election.dataModel.CategoryElementEntity;
import ir.blacksparrow.imh_student_election.dataModel.PermissionEntity;
import ir.blacksparrow.imh_student_election.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepository extends ParentRepository {
    private final IPermissionRepository permissionRepository;


    public PermissionRepository(ModelMapper modelMapper, IPermissionRepository permissionRepository) {
        super(modelMapper);
        this.permissionRepository = permissionRepository;

    }

    public List<PermissionDto> findAll() {
        List<PermissionEntity> permissionEntityList = permissionRepository.findAll();
        return mapList(permissionEntityList, PermissionDto.class);
    }

    public List<PermissionDto> findAll(int offset, int size) {
        List<PermissionEntity> permissionEntityList = permissionRepository.findAll(offset, size);
        return mapList(permissionEntityList, PermissionDto.class);
    }

    public Optional<PermissionDto> getById(Long id) {
        PermissionEntity permissionEntityList = permissionRepository.getById(id);
        return Optional.of(getModelMapper().map(permissionEntityList, PermissionDto.class));
    }

    public Optional<PermissionDto> insertAndUpdate(PermissionDto permissionDto) {
        PermissionEntity permissionEntity = getModelMapper().map(permissionDto, PermissionEntity.class);
        permissionEntity = permissionRepository.save(permissionEntity);
        return Optional.of(getModelMapper().map(permissionEntity, PermissionDto.class));
    }

    public List<PermissionDto> insertAndUpdateAll(List<PermissionDto> permissionDtoList) {
        List<PermissionEntity> permissionEntityList = mapList(permissionDtoList, PermissionEntity.class);
        permissionEntityList = permissionRepository.saveAll(permissionEntityList);
        return mapList(permissionEntityList, PermissionDto.class);
    }
}