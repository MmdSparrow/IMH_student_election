package ir.blacksparrow.websitebackend.repository.person;

import ir.blacksparrow.websitebackend.dataModel.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<PersonEntity, String> {
}
