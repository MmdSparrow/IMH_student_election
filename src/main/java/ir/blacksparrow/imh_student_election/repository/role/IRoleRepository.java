package ir.blacksparrow.imh_student_election.repository.role;

import ir.blacksparrow.imh_student_election.dataModel.RoleEntity;
import ir.blacksparrow.imh_student_election.dataModel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, String> {
    @Query(value = "SELECT *" +
            " FROM BS_ROLE" +
            " OFFSET ?1" +
            " LIMIT ?2"
            , nativeQuery = true)
    List<RoleEntity> findAll(int offset, int size);

    @Query(value = "SELECT * FROM BS_ROLE WHERE TITLE= ?1",
            nativeQuery = true)
    RoleEntity getByTitle(String title);
}
