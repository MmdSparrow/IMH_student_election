package ir.blacksparrow.imh_student_election.view.controller.user;

import ir.blacksparrow.imh_student_election.business.dto.UserDto;
import ir.blacksparrow.imh_student_election.business.sevice.categoryElement.CategoryElementService;
import ir.blacksparrow.imh_student_election.business.sevice.registration.IRegistrationService;
import ir.blacksparrow.imh_student_election.business.sevice.registration.RegistrationService;
import ir.blacksparrow.imh_student_election.business.sevice.role.IRoleService;
import ir.blacksparrow.imh_student_election.business.sevice.role.RoleService;
import ir.blacksparrow.imh_student_election.view.controller.ParentController;
import ir.blacksparrow.imh_student_election.view.viewDto.user.viewDto.UserViewDtoChild;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController extends ParentController {

    private final IRegistrationService registrationService;
    private final IRoleService roleService;

    public UserController(ModelMapper modelMapper, IRegistrationService registrationService, IRoleService roleService) {
        super(modelMapper);
        this.registrationService = registrationService;
        this.roleService = roleService;
    }

    @PostMapping
    public String register(
            @Valid @RequestBody UserViewDtoChild request
    ) throws IllegalAccessException {
        UserDto userDto=getModelMapper().map(request, UserDto.class);
        userDto.setRoleDtoChild(roleService.getRoleById(request
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

