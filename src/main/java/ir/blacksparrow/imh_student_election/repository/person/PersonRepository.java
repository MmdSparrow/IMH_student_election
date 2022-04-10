package ir.blacksparrow.imh_student_election.repository.person;

import ir.blacksparrow.imh_student_election.business.dto.PersonDto;
import ir.blacksparrow.imh_student_election.dataModel.PersonEntity;
import ir.blacksparrow.imh_student_election.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class PersonRepository extends ParentRepository {
    private final IPersonRepository personRepository;

    public PersonRepository(ModelMapper modelMapper, IPersonRepository personRepository) {
        super(modelMapper);
        this.personRepository = personRepository;
    }

    public Optional<PersonDto> insertAndUpdate(PersonDto personDto) {
        PersonEntity personEntity = getModelMapper().map(personDto, PersonEntity.class);
        personEntity = personRepository.save(personEntity);
        return Optional.of(getModelMapper().map(personEntity, PersonDto.class));
    }
}
