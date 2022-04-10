package ir.blacksparrow.imh_student_election.repository.role;

import ir.blacksparrow.imh_student_election.business.dto.RoleDtoChild;
import ir.blacksparrow.imh_student_election.dataModel.RoleEntity;
import ir.blacksparrow.imh_student_election.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class RoleRepository extends ParentRepository {
    private final IRoleRepository roleRepository;


    public RoleRepository(ModelMapper modelMapper, IRoleRepository roleRepository) {
        super(modelMapper);
        this.roleRepository = roleRepository;

        TypeMap<RoleDtoChild, RoleEntity> propertyMapper = getModelMapper().createTypeMap(RoleDtoChild.class, RoleEntity.class);
        propertyMapper.addMappings(mp->{
            mp.map(RoleDtoChild::getPermissions, RoleEntity::setPermissions);
        });

    }

    public List<RoleDtoChild> findAll() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        return mapList(roleEntityList, RoleDtoChild.class);
    }

    public List<RoleDtoChild> findAll(int offset, int size) {
        List<RoleEntity> roleEntityList = roleRepository.findAll(offset, size);
        return mapList(roleEntityList, RoleDtoChild.class);
    }

    public Optional<RoleDtoChild> getById(Long id) {
        RoleEntity roleEntity = roleRepository.getById(id);
        return Optional.of(getModelMapper().map(roleEntity, RoleDtoChild.class));
    }

    public Optional<RoleDtoChild> insertAndUpdate(RoleDtoChild roleDtoChild) {
        System.out.println("11111111111111111111111111111111111");
        RoleEntity roleEntity = getModelMapper().map(roleDtoChild, RoleEntity.class);
        System.out.println("22222222222222222222222222222222222");

        roleEntity = roleRepository.save(roleEntity);
        return Optional.of(getModelMapper().map(roleEntity, RoleDtoChild.class));
    }

    public List<RoleDtoChild> insertAndUpdateAll(List<RoleDtoChild> roleDtoChildList) {
        List<RoleEntity> roleEntityList = mapList(roleDtoChildList, RoleEntity.class);
        roleEntityList = roleRepository.saveAll(roleEntityList);
        return mapList(roleEntityList, RoleDtoChild.class);
    }
}
