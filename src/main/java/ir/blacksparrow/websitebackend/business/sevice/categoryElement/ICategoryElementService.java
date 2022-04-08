package ir.blacksparrow.websitebackend.business.sevice.categoryElement;

import ir.blacksparrow.websitebackend.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.websitebackend.business.dto.CategoryElementDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryElementService {
    List<CategoryElementDtoChild> getCategoryElementList();
    List<CategoryElementDtoChild> getCategoryElementList(int offset, int size);
    List<CategoryElementDtoChild> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild);
    List<CategoryElementDtoChild> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild, int offset, int size);
    Optional<CategoryElementDtoChild> getCategoryElementById(long id);

    Optional<CategoryElementDtoChild> insertAndUpdateCategoryElement(CategoryElementDto categoryElementDto);

    List<CategoryElementDtoChild> insertAndUpdateAllCategoryElement(List<CategoryElementDto> categoryElementDtoList);

    void deleteCategoryElement(Long id);
}
