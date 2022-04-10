package ir.blacksparrow.imh_student_election.business.sevice.permission;

import ir.blacksparrow.imh_student_election.business.dto.CategoryElementDto;
import ir.blacksparrow.imh_student_election.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.PermissionDto;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    List<PermissionDto> getPermissionList();
    List<PermissionDto> getPermissionList(int offset, int size);
//    List<PermissionDto> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild);
//    List<PermissionDto> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild, int offset, int size);
    Optional<PermissionDto> getPermissionByTitle(String title);

    Optional<PermissionDto> insertAndUpdatePermission(PermissionDto permissionDto);

//    List<PermissionDto> insertAndUpdateAllPermission(List<PermissionDto> permissionDtoList);

    void deletePermission(Long id);
}
