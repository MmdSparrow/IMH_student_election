package ir.blacksparrow.imh_student_election.repository.role;

import ir.blacksparrow.imh_student_election.dataModel.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "SELECT *" +
            " FROM BS_ROLE" +
            " OFFSET ?1" +
            " LIMIT ?2"
            , nativeQuery = true)
    List<RoleEntity> findAll(int offset, int size);
}
