package ir.blacksparrow.websitebackend.business.sevice.category;

import ir.blacksparrow.websitebackend.business.dto.CategoryDto;
import ir.blacksparrow.websitebackend.repository.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryDto> getCategoryList(int offset, int size) {
        return categoryRepository.findAll(offset, size);
    }

    @Override
    public List<CategoryDto> searchCategory(String code, String title) {
        return categoryRepository.search(code, title);
    }

    @Override
    public List<CategoryDto> searchCategory(String code, String title, int offset, int size) {
        return categoryRepository.search(code, title, offset, size);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public Optional<CategoryDto> insertAndUpdateCategory(CategoryDto categoryDto) {
        return categoryRepository.insertAndUpdate(categoryDto);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.delete(id);
    }
}
