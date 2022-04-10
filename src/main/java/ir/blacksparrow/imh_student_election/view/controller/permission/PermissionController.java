package ir.blacksparrow.imh_student_election.view.controller.permission;

import ir.blacksparrow.imh_student_election.business.dto.*;
import ir.blacksparrow.imh_student_election.business.sevice.categoryElement.ICategoryElementService;
import ir.blacksparrow.imh_student_election.business.sevice.permission.IPermissionService;
import ir.blacksparrow.imh_student_election.view.controller.ParentController;
import ir.blacksparrow.imh_student_election.view.controller.categoryElement.validator.CategoryElementValidator;
import ir.blacksparrow.imh_student_election.view.controller.permission.validator.PermissionValidator;
import ir.blacksparrow.imh_student_election.view.viewDto.categoryElement.viewDto.CategoryElementViewDto;
import ir.blacksparrow.imh_student_election.view.viewDto.permission.viewDto.PermissionViewDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permission")
public class PermissionController extends ParentController {

    private final IPermissionService permissionService;

    public PermissionController(ModelMapper modelMapper, IPermissionService permissionService) {
        super(modelMapper);
        this.permissionService = permissionService;

//        TypeMap<PermissionViewDto, PermissionDto> propertyMapper = getModelMapper().createTypeMap(PermissionViewDto.class, PermissionDto.class);
//        propertyMapper.addMappings(mp->{
//            mp.map(PermissionViewDto::getCategoryId, PermissionDto::setCategoryId);
//            mp.skip(PermissionDto::setId);
//        });
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> findAllPermissions(
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        if (!PermissionValidator.isValidSizeOffset(size, offset))
            return sendResponse(new ResponseDto(false, "invalid size or offset", null), HttpStatus.BAD_REQUEST);
        if (size == null) {
            try {
                List<PermissionDto> permissionDtoList = permissionService.getPermissionList();
                return sendResponse(new ResponseDto(true, null, permissionDtoList.size(), permissionDtoList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                List<PermissionDto> permissionDtoList = permissionService.getPermissionList(offset, size);
                return sendResponse(new ResponseDto(true, null, permissionDtoList.size(), permissionDtoList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> getPermissionById(
            @PathVariable Long id
    ) {
        try {
            Optional<PermissionDto> permissionDto = permissionService.getPermissionById(id);
            return sendResponse(new ResponseDto(true, null, permissionDto), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(
//            path = "/search",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<ResponseDto> searchPermission(
//            @RequestParam(value = "title", required = false) String title,
//            @RequestParam(value = "offset", required = false) Integer offset,
//            @RequestParam(value = "size", required = false) Integer size
//    ) {
//        if (!PermissionValidator.isValidSizeOffset(size, offset))
//            return sendResponse(new ResponseDto(false, "invalid size or offset", null), HttpStatus.BAD_REQUEST);
//
//        if (size == null) {
//            try {
//                PermissionDto permissionDto = new PermissionDto(title);
//                List<PermissionDto> permissionDtoList = permissionDto.search(permissionDto);
//                return sendResponse(new ResponseDto(true, null, permissionDtoList.size(), permissionDtoList), HttpStatus.OK);
//            } catch (Exception e) {
//                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            try {
//                PermissionDto permissionDto = new PermissionDto(title);
//                List<PermissionDto> permissionDtoList = permissionDto.search(permissionDto, offset, size);
//                return sendResponse(new ResponseDto(true, null, permissionDtoList.size(), permissionDtoList), HttpStatus.OK);
//            } catch (Exception e) {
//                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
//            }
//        }
//    }


    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> addPermission(
            @Valid @RequestBody PermissionViewDto permissionViewDto
    ) {
        try {
            PermissionDto permissionDto = getModelMapper().map(permissionViewDto, PermissionDto.class);
            permissionDto.setId(null); //TODO
            permissionDto = permissionService.insertAndUpdatePermission(permissionDto).orElse(null);
            return sendResponse(new ResponseDto(true, null, permissionDto), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> editPermission(
            @Valid @RequestBody PermissionViewDto permissionViewDto,
            @PathVariable Long id
    ) {
        try {
            PermissionDto permissionDto = getModelMapper().map(permissionViewDto, PermissionDto.class);
            permissionDto.setId(id); //TODO
            permissionDto = permissionService.insertAndUpdatePermission(permissionDto).orElse(null);
            return sendResponse(new ResponseDto(true, null, permissionDto), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> deletePermission(
            @PathVariable Long id
    ) {
        try {
            permissionService.deletePermission(id);
            return sendResponse(new ResponseDto(true, null, null), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}