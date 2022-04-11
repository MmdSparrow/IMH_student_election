package ir.blacksparrow.imh_student_election.business.sevice.role;

import ir.blacksparrow.imh_student_election.business.dto.*;
import ir.blacksparrow.imh_student_election.repository.permission.PermissionRepository;
import ir.blacksparrow.imh_student_election.repository.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public List<RoleDtoChild> getRoleList() {
        return roleRepository.findAll();
    }

    @Override
    public List<RoleDtoChild> getRoleList(int offset, int size) {
        return roleRepository.findAll(offset, size);
    }

    @Override
    public Optional<RoleDtoChild> getRoleByTitle(String title) {
        return Optional.of(roleRepository.getByTitle(title));
    }

    @Override
    public Optional<RoleDtoChild> insertAndUpdateRole(RoleDto roleDto) {
        RoleDtoChild roleDtoChild = new RoleDtoChild();
        roleDtoChild.setTitle(roleDto.getTitle());
        List<PermissionDto> permissionDtoList=new ArrayList<>();
        for(String title: roleDto.getPermissionsTitle())
            permissionDtoList.add(permissionRepository.getByTitle(title).orElse(null));
        roleDtoChild.setPermissions(permissionDtoList);
        return roleRepository.insertAndUpdate(roleDtoChild);
    }

    @Override
    public void deleteRole(Long id) {

    }
}
