package ir.blacksparrow.websitebackend.view.controller.category;

import ir.blacksparrow.websitebackend.business.dto.CategoryDto;
import ir.blacksparrow.websitebackend.business.dto.ResponseDto;
import ir.blacksparrow.websitebackend.business.sevice.category.CategoryService;
import ir.blacksparrow.websitebackend.business.sevice.category.ICategoryService;
import ir.blacksparrow.websitebackend.exception.CustomException;
import ir.blacksparrow.websitebackend.view.controller.ParentController;
import ir.blacksparrow.websitebackend.view.controller.category.validator.CategoryValidator;
import ir.blacksparrow.websitebackend.view.viewDto.category.viewDto.CategoryViewDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

//@Transactional
@RestController
@RequestMapping("/category")
public class CategoryController extends ParentController {

    private final ICategoryService categoryService;

    public CategoryController(ModelMapper modelMapper, CategoryService category) {
        super(modelMapper);
        this.categoryService = category;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> findAllCategories(
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size,
            HttpServletRequest request
    ) {
        if (!CategoryValidator.isValidSizeOffset(size, offset))
            throw new CustomException(request, "invalidSizeOrOffset");
        if (size == null) {
            try {
                List<CategoryDto> categoryDtoList = categoryService.getCategoryList();
                return sendResponse(new ResponseDto(true, null, categoryDtoList.size(), categoryDtoList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                List<CategoryDto> categoryDtoList = categoryService.getCategoryList(offset, size);
                return sendResponse(new ResponseDto(true, null, categoryDtoList.size(), categoryDtoList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> getCategoryById(
            @PathVariable long id
    ) {
        try {
            Optional<CategoryDto> categoryDto = categoryService.getCategoryById(id);
            return sendResponse(new ResponseDto(true, null, categoryDto.orElse(null)), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> searchCategory(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        if (!CategoryValidator.isValidSizeOffset(size, offset))
            return sendResponse(new ResponseDto(false, "invalid size or offset", null), HttpStatus.BAD_REQUEST);

        if (size == null) {
            try {
                List<CategoryDto> categoryDtoList = categoryService.searchCategory(code, title);
                return sendResponse(new ResponseDto(true, null, categoryDtoList.size(), categoryDtoList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        } else {
            try {
                List<CategoryDto> categoryDtoList = categoryService.searchCategory(code, title, offset, size);
                return sendResponse(new ResponseDto(true, null, categoryDtoList.size(), categoryDtoList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }


    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> addCategory(
            @RequestBody CategoryViewDto category
    ) {
        try {
            Optional<CategoryDto> categoryDto = categoryService.insertAndUpdateCategory(getModelMapper().map(category, CategoryDto.class));
            return sendResponse(new ResponseDto(true, null, categoryDto), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> editCategory(
            @RequestBody CategoryViewDto category,
            @PathVariable long id
    ) {
        try {
            CategoryDto categoryDto = getModelMapper().map(category, CategoryDto.class);
            categoryDto.setId(id);
            categoryDto = categoryService.insertAndUpdateCategory(categoryDto).orElse(null);
            return sendResponse(new ResponseDto(true, null, categoryDto), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> deleteCategory(
            @PathVariable long id
    ) {
        try {
            categoryService.deleteCategory(id);
            return sendResponse(new ResponseDto(true, null, null), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
