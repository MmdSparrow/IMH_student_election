package ir.blacksparrow.imh_student_election.repository.user;

import ir.blacksparrow.imh_student_election.business.dto.RoleDtoChild;
import ir.blacksparrow.imh_student_election.business.dto.UserDto;
import ir.blacksparrow.imh_student_election.dataModel.UserEntity;
import ir.blacksparrow.imh_student_election.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public class UserRepository extends ParentRepository {

    private final IUserRepository userRepository;

    @Autowired
    public UserRepository(ModelMapper modelMapper, IUserRepository userRepository) {
        super(modelMapper);
        this.userRepository = userRepository;
//
//        TypeMap<UserDto, UserEntity> propertyMapper = modelMapper.createTypeMap(UserDto.class, UserEntity.class);
//        propertyMapper.addMappings(mp->{
//            mp.map(UserDto::getCategoryElement, UserEntity::setCategoryElement);
//            mp.map(UserDto::getPerson, UserEntity::setPerson);
//        });
    }

    public Optional<UserDto> findByEmail(String emailAddress){
        List<UserEntity> userEntityList=userRepository.findTopByOrderByEmailAddress(emailAddress);
        UserEntity userEntity;
        UserDto userDto=null;
        if(userEntityList!= null && userEntityList.size()!=0){
            userEntity=userRepository.findTopByOrderByEmailAddress(emailAddress).get(0);
            userDto= getModelMapper().map(userEntity, UserDto.class);
            userDto.setRole(getModelMapper().map(userEntity.getRole(), RoleDtoChild.class));
        }
        return (userEntityList== null || userEntityList.size()==0) ? Optional.empty(): Optional.of(userDto);
    }

    public Optional<UserDto> findByUsername(String username){
        return Optional.of(getModelMapper().map(userRepository.findByUsername(username), UserDto.class));
    }

    public Optional<UserDto> insert(UserDto user){
        UserEntity userEntity = getModelMapper().map(user, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return Optional.of(getModelMapper().map(userEntity, UserDto.class));
    }
}
