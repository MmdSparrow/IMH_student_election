package ir.blacksparrow.imh_student_election.repository.person;

import ir.blacksparrow.imh_student_election.dataModel.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, String> {
}
