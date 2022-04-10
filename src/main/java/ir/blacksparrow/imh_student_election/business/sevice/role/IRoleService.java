package ir.blacksparrow.imh_student_election.business.sevice.role;

import ir.blacksparrow.imh_student_election.business.dto.RoleDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.RoleDto;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<RoleDtoChild> getRoleList();
    List<RoleDtoChild> getRoleList(int offset, int size);
//    List<CategoryElementDtoChild> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild);
//    List<CategoryElementDtoChild> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild, int offset, int size);
    Optional<RoleDtoChild> getRoleByTitle(String title);
    Optional<RoleDtoChild> insertAndUpdateRole(RoleDto roleDto);

//    Optional<RoleDtoChild> insertAndUpdateRole(RoleDto roleDto);

//    List<CategoryElementDtoChild> insertAndUpdateAllRole(List<CategoryElementDto> categoryElementDtoList);

    void deleteRole(Long id);
}
