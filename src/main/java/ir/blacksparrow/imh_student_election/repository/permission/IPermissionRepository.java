package ir.blacksparrow.imh_student_election.repository.permission;

import ir.blacksparrow.imh_student_election.dataModel.PermissionEntity;
import ir.blacksparrow.imh_student_election.dataModel.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPermissionRepository extends JpaRepository<PermissionEntity, String> {
    @Query(value = "SELECT *" +
            " FROM BS_PERMISSION" +
            " OFFSET ?1" +
            " LIMIT ?2"
            , nativeQuery = true)
    List<PermissionEntity> findAll(int offset, int size);

    @Query(value = "SELECT * FROM BS_PERMISSION WHERE TITLE= ?1",
            nativeQuery = true)
    PermissionEntity getByTitle(String title);
}

