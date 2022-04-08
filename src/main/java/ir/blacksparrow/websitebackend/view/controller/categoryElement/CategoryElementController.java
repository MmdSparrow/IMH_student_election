package ir.blacksparrow.websitebackend.view.controller.categoryElement;

import ir.blacksparrow.websitebackend.business.dto.CategoryDto;
import ir.blacksparrow.websitebackend.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.websitebackend.business.dto.CategoryElementDto;
import ir.blacksparrow.websitebackend.business.dto.ResponseDto;
import ir.blacksparrow.websitebackend.business.sevice.categoryElement.ICategoryElementService;
import ir.blacksparrow.websitebackend.view.controller.ParentController;
import ir.blacksparrow.websitebackend.view.controller.categoryElement.validator.CategoryElementValidator;
import ir.blacksparrow.websitebackend.view.viewDto.categoryElement.viewDto.CategoryElementViewDto;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category-element")
public class CategoryElementController extends ParentController {

    private final ICategoryElementService categoryElementService;

    public CategoryElementController(ModelMapper modelMapper, ICategoryElementService categoryElementService) {
        super(modelMapper);
        this.categoryElementService = categoryElementService;

        TypeMap<CategoryElementViewDto, CategoryElementDto> propertyMapper = getModelMapper().createTypeMap(CategoryElementViewDto.class, CategoryElementDto.class);
        propertyMapper.addMappings(mp->{
            mp.map(CategoryElementViewDto::getCategoryId, CategoryElementDto::setCategoryId);
            mp.skip(CategoryElementDto::setId);
        });
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> findAllCategoryElements(
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        if (!CategoryElementValidator.isValidSizeOffset(size, offset))
            return sendResponse(new ResponseDto(false, "invalid size or offset", null), HttpStatus.BAD_REQUEST);
        if(size==null){
            try{
                List<CategoryElementDtoChild> categoryElementDtoChildList = categoryElementService.getCategoryElementList();
                return sendResponse(new ResponseDto(true,null, categoryElementDtoChildList.size(), categoryElementDtoChildList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }else {
            try{
                List<CategoryElementDtoChild> categoryElementDtoChildList = categoryElementService.getCategoryElementList(offset, size);
                return sendResponse(new ResponseDto(true,null, categoryElementDtoChildList.size(), categoryElementDtoChildList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto>  getCategoryElementById(
            @PathVariable Long id
    ) {
        try {
            Optional<CategoryElementDtoChild> categoryElementDto = categoryElementService.getCategoryElementById(id);
            return sendResponse(new ResponseDto(true,null,categoryElementDto), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false,e.getMessage(),null),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            path = "/search",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> searchCategoryElement(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "CategoryId", required = false) Long categoryId,
            @RequestParam(value = "CategoryCode", required = false) String categoryCode,
            @RequestParam(value = "CategoryTitle", required = false) String categoryTitle,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        if(!CategoryElementValidator.isValidSizeOffset(size,offset))
            return sendResponse(new ResponseDto(false,"invalid size or offset",null), HttpStatus.BAD_REQUEST);

        if(size==null){
            try{
                CategoryDto categoryDto=new CategoryDto(categoryId, categoryCode, categoryTitle);
                CategoryElementDtoChild categoryElementDtoChild =new CategoryElementDtoChild(code,title,categoryDto);
                List<CategoryElementDtoChild> categoryElementDtoChildList = categoryElementService.searchCategoryElement(categoryElementDtoChild);
                return sendResponse(new ResponseDto(true,null, categoryElementDtoChildList.size(), categoryElementDtoChildList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }else {
            try{
                CategoryDto categoryDto=new CategoryDto(categoryId, categoryCode, categoryTitle);
                CategoryElementDtoChild categoryElementDtoChild =new CategoryElementDtoChild(code,title,categoryDto);
                List<CategoryElementDtoChild> categoryElementDtoChildList = categoryElementService.searchCategoryElement(categoryElementDtoChild, offset, size);
                return sendResponse(new ResponseDto(true,null, categoryElementDtoChildList.size(), categoryElementDtoChildList), HttpStatus.OK);
            } catch (Exception e) {
                return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
            }
        }
    }



    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> addCategoryElement(
            @RequestBody CategoryElementViewDto categoryElement
    ) {
        try {
            CategoryElementDto categoryElementDto=getModelMapper().map(categoryElement, CategoryElementDto.class);
            categoryElementDto.setId(null); //TODO
            Optional<CategoryElementDtoChild> optionalCategoryElementDtoChild = categoryElementService.insertAndUpdateCategoryElement(categoryElementDto);
            return sendResponse(new ResponseDto(true, null, optionalCategoryElementDtoChild), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false, e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> editCategoryElement(
            @RequestBody CategoryElementViewDto categoryElement,
            @PathVariable Long id
    ) {
        try {
            CategoryElementDto categoryElementDto = getModelMapper().map(categoryElement, CategoryElementDto.class);
            categoryElementDto.setId(id);
            CategoryElementDtoChild categoryElementDtoChild = categoryElementService.insertAndUpdateCategoryElement(categoryElementDto).orElse(null);
            return sendResponse(new ResponseDto(true,null, categoryElementDtoChild), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false,e.getMessage(),null),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseDto> deleteCategory(
            @PathVariable Long id
    ) {
        try {
            categoryElementService.deleteCategoryElement(id);
            return sendResponse(new ResponseDto(true,null,null), HttpStatus.OK);
        } catch (Exception e) {
            return sendResponse(new ResponseDto(false,e.getMessage(),null),HttpStatus.BAD_REQUEST);
        }
    }
}
