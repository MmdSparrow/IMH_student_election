package ir.blacksparrow.websitebackend.business.sevice.categoryElement;

import ir.blacksparrow.websitebackend.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.websitebackend.business.dto.CategoryElementDto;
import ir.blacksparrow.websitebackend.repository.category.CategoryRepository;
import ir.blacksparrow.websitebackend.repository.categoryElement.CategoryElementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryElementService implements ICategoryElementService {

    private final CategoryElementRepository categoryElementRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryElementDtoChild> getCategoryElementList() {
        return categoryElementRepository.findAll();
    }

    @Override
    public List<CategoryElementDtoChild> getCategoryElementList(int offset, int size) {
        return categoryElementRepository.findAll(offset, size);
    }

    @Override
    public List<CategoryElementDtoChild> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild) {
        return categoryElementRepository.search(categoryElementDtoChild.getCode(), categoryElementDtoChild.getTitle(), categoryElementDtoChild.getCategory().getId(), categoryElementDtoChild.getCategory().getCode(), categoryElementDtoChild.getCategory().getTitle());
    }

    @Override
    public List<CategoryElementDtoChild> searchCategoryElement(CategoryElementDtoChild categoryElementDtoChild, int offset, int size) {
        return categoryElementRepository.search(
                categoryElementDtoChild.getCode(),
                categoryElementDtoChild.getTitle(),
                categoryElementDtoChild.getCategory().getId(),
                categoryElementDtoChild.getCategory().getCode(),
                categoryElementDtoChild.getCategory().getTitle(),
                offset,
                size);
    }

    @Override
    public Optional<CategoryElementDtoChild> getCategoryElementById(long id) {
        return categoryElementRepository.getById(id);
    }

    @Override
    public Optional<CategoryElementDtoChild> insertAndUpdateCategoryElement(CategoryElementDto categoryElementDto) {
        CategoryElementDtoChild categoryElementDtoChild =new CategoryElementDtoChild(
                categoryElementDto.getId(),
                categoryElementDto.getCode(),
                categoryElementDto.getTitle(),
                categoryRepository.getById(categoryElementDto.getCategoryId()).orElse(null));
        return categoryElementRepository.insertAndUpdate(categoryElementDtoChild);
    }

    @Override
    public List<CategoryElementDtoChild> insertAndUpdateAllCategoryElement(List<CategoryElementDto> categoryElementDtoList) {
        List<CategoryElementDtoChild> categoryElementDtoChildList =new ArrayList<>();
        for(CategoryElementDto categoryElementDto : categoryElementDtoList){
            CategoryElementDtoChild categoryElementDtoChild =new CategoryElementDtoChild(
                    categoryElementDto.getId(),
                    categoryElementDto.getCode(),
                    categoryElementDto.getTitle(),
                    categoryRepository.getById(categoryElementDto.getCategoryId()).orElse(null));
            categoryElementDtoChildList.add(categoryElementDtoChild);
        }
        return categoryElementRepository.insertAndUpdateAll(categoryElementDtoChildList);
    }

    @Override
    public void deleteCategoryElement(Long id) {
        categoryElementRepository.delete(id);
    }
}
