package ir.blacksparrow.imh_student_election.view.controller.role;

import ir.blacksparrow.imh_student_election.business.dto.PermissionDto;
import ir.blacksparrow.imh_student_election.business.dto.ResponseDto;
import ir.blacksparrow.imh_student_election.business.dto.RoleDto;
import ir.blacksparrow.imh_student_election.business.dto.RoleDtoChild;
import ir.blacksparrow.imh_student_election.business.sevice.permission.IPermissionService;
import ir.blacksparrow.imh_student_election.business.sevice.role.IRoleService;
import ir.blacksparrow.imh_student_election.view.controller.ParentController;
import ir.blacksparrow.imh_student_election.view.controller.permission.validator.PermissionValidator;
import ir.blacksparrow.imh_student_election.view.viewDto.permission.viewDto.PermissionViewDto;
import ir.blacksparrow.imh_student_election.view.viewDto.role.viewDto.RoleViewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/role")
public class RoleController extends ParentController {

    private final IRoleService roleService;

    public RoleController(ModelMapper modelMapper, IRoleService roleService) {
        super(modelMapper);
        this.roleService = roleService;

        TypeMap<RoleViewDto, RoleDto> propertyMapper = getModelMapper().createTypeMap(RoleViewDto.class, RoleDto.class);
        propertyMapper.addMappings(mp->{
//            mp.map(RoleViewDto::getPermissionsId, RoleDto::setPermissionsId);
            mp.skip(RoleDto::setPermissionsTitle); //todo: it must to be fix later.........................
        });
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> findAllrolse(
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        if (!PermissionValidator.isValidSizeOffset(size, offset))
            return sendResponse(new ResponseDto(false, "invalid size or offset", null), HttpStatus.BAD_REQUEST);
        if (size == null) {
            try {
                List<RoleDtoChild> roleDtoChildList = roleService.getRoleList();
                return sendResponse(new ResponseDto(true, null, roleDtoChildList.size(), roleDtoChildList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                List<RoleDtoChild> roleDtoChildList = roleService.getRoleList(offset, size);
                return sendResponse(new ResponseDto(true, null, roleDtoChildList.size(), roleDtoChildList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(
            path = "/{title}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> getRoleById(
            @PathVariable String title
    ) {
        try {
            Optional<RoleDtoChild> roleDtoChild = roleService.getRoleByTitle(title);
            return sendResponse(new ResponseDto(true, null, roleDtoChild), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> addRole(
            @Valid @RequestBody RoleViewDto roleViewDto
    ) {
        try {
            RoleDto roleDto=new RoleDto();
            roleDto.setPermissionsTitle(new ArrayList<>());
            roleDto.setTitle(roleViewDto.getTitle()); //TODO: it's now without model mapper............................
            roleDto.setPermissionsTitle(roleViewDto.getPermissionsTitle());
            RoleDtoChild roleDtoChild = roleService.insertAndUpdateRole(roleDto).orElse(null);
            return sendResponse(new ResponseDto(true, null, roleDtoChild), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> editRole(
            @Valid @RequestBody RoleViewDto roleViewDto
    ) {
        try {
            RoleDto roleDto = getModelMapper().map(roleViewDto, RoleDto.class);
            RoleDtoChild roleDtoChild = roleService.insertAndUpdateRole(roleDto).orElse(null);
            return sendResponse(new ResponseDto(true, null, roleDtoChild), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> deleteRole(
            @PathVariable Long id
    ) {
        try {
            roleService.deleteRole(id);
            return sendResponse(new ResponseDto(true, null, null), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}