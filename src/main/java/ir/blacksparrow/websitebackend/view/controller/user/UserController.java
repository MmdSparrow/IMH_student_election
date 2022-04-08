package ir.blacksparrow.websitebackend.view.controller.user;

import ir.blacksparrow.websitebackend.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.websitebackend.business.dto.PersonDto;
import ir.blacksparrow.websitebackend.business.dto.UserDto;
import ir.blacksparrow.websitebackend.business.sevice.categoryElement.CategoryElementService;
import ir.blacksparrow.websitebackend.business.sevice.registration.RegistrationService;
import ir.blacksparrow.websitebackend.dataModel.CategoryElementEntity;
import ir.blacksparrow.websitebackend.view.controller.ParentController;
import ir.blacksparrow.websitebackend.view.viewDto.user.viewDto.UserViewDto;
import ir.blacksparrow.websitebackend.view.viewDto.user.viewDto.UserViewDtoChild;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends ParentController {

    private final RegistrationService registrationService;
    private final CategoryElementService categoryElementService;

    public UserController(ModelMapper modelMapper, RegistrationService registrationService, CategoryElementService categoryElementService) {
        super(modelMapper);
        this.registrationService = registrationService;
        this.categoryElementService = categoryElementService;
    }

    @PostMapping
    public String register(
            @Valid @RequestBody UserViewDtoChild request
    ) throws IllegalAccessException {
        UserDto userDto=getModelMapper().map(request, UserDto.class);
        userDto.setCategoryElement(categoryElementService.getCategoryElementById(request
                .getCategoryElementId()).
                orElse(null));
        return registrationService.register(userDto);
    }

    @GetMapping(
            path = "/confirm",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String confirm(
//            @RequestHeader("confirmToken") String confirmToken
            @RequestParam("confirmToken") String confirmToken
    ) {
        System.out.println(confirmToken);
        return registrationService.confirmToken(confirmToken);
    }

}

