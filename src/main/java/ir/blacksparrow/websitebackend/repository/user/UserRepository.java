package ir.blacksparrow.websitebackend.repository.user;

import ir.blacksparrow.websitebackend.business.dto.CategoryElementDto;
import ir.blacksparrow.websitebackend.business.dto.UserDto;
import ir.blacksparrow.websitebackend.dataModel.CategoryElementEntity;
import ir.blacksparrow.websitebackend.dataModel.CategoryEntity;
import ir.blacksparrow.websitebackend.dataModel.PersonEntity;
import ir.blacksparrow.websitebackend.dataModel.UserEntity;
import ir.blacksparrow.websitebackend.repository.ParentRepository;
import ir.blacksparrow.websitebackend.view.viewDto.categoryElement.viewDto.CategoryElementViewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
        return (userEntityList== null || userEntityList.size()==0) ? Optional.empty(): Optional.of(getModelMapper().map(userRepository.findTopByOrderByEmailAddress(emailAddress).get(0), UserDto.class));
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
