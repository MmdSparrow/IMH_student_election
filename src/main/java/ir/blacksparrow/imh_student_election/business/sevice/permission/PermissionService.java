package ir.blacksparrow.imh_student_election.business.sevice.permission;

import ir.blacksparrow.imh_student_election.business.dto.PermissionDto;
import ir.blacksparrow.imh_student_election.repository.permission.PermissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PermissionService implements IPermissionService {

    private final PermissionRepository permissionRepository;


    @Override
    public List<PermissionDto> getPermissionList() {
        return permissionRepository.findAll();
    }

    @Override
    public List<PermissionDto> getPermissionList(int offset, int size) {
        return permissionRepository.findAll(offset, size);
    }

    @Override
    public Optional<PermissionDto> getPermissionById(long id) {
        return permissionRepository.getById(id);
    }

    @Override
    public Optional<PermissionDto> insertAndUpdatePermission(PermissionDto permissionDto) {
        return permissionRepository.insertAndUpdate(permissionDto);
    }

    @Override
    public void deletePermission(Long id) {
    }
}
