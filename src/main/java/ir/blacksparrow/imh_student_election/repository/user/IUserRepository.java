package ir.blacksparrow.imh_student_election.repository.user;

import ir.blacksparrow.imh_student_election.dataModel.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity,String> {
    @Query(value = "SELECT * FROM BS_USER WHERE EMAIL_ADDRESS = ?1",
            nativeQuery = true)
//    @Query
    List<UserEntity> findTopByOrderByEmailAddress(String emailAddress);

//    @Query(value = "SELECT * FROM BS_USER WHERE USERNAME = ?1",
//            nativeQuery = true)
    @Query
    Optional<UserEntity> findByUsername(String username);
//    Optional<UserEntity> insert(UserEntity userEntity);

}
