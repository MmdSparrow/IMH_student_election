package ir.blacksparrow.websitebackend.repository.category;

import ir.blacksparrow.websitebackend.business.dto.CategoryDto;
import ir.blacksparrow.websitebackend.dataModel.CategoryEntity;
import ir.blacksparrow.websitebackend.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CategoryRepository extends ParentRepository {
    private final ICategoryRepository categoryRepository;

    public CategoryRepository(ModelMapper modelMapper, ICategoryRepository categoryRepository) {
        super(modelMapper);
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAll() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return mapList(categoryEntityList, CategoryDto.class);
    }

    public List<CategoryDto> findAll(int offset, int size) {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll(offset, size);
        return mapList(categoryEntityList, CategoryDto.class);
    }

    public Optional<CategoryDto> getById(long id) {
        CategoryEntity categoryEntity = categoryRepository.getById(id);
        return Optional.of(getModelMapper().map(categoryEntity, CategoryDto.class));
    }

    public Optional<CategoryDto> insertAndUpdate(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = getModelMapper().map(categoryDto, CategoryEntity.class);
        categoryEntity = categoryRepository.save(categoryEntity);
        return Optional.of(getModelMapper().map(categoryEntity, CategoryDto.class));
    }

    public List<CategoryDto> search(String code, String title) {
        List<CategoryEntity> categoryEntityList = categoryRepository.search(code, title);
        return mapList(categoryEntityList, CategoryDto.class);
    }

    public List<CategoryDto> search(String code, String title, int offset, int size) {
        List<CategoryEntity> categoryEntityList = categoryRepository.search(code, title, offset, size);
        return mapList(categoryEntityList, CategoryDto.class);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


}
