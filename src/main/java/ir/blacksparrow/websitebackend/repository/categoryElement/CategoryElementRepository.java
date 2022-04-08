package ir.blacksparrow.websitebackend.repository.categoryElement;

import ir.blacksparrow.websitebackend.business.dto.CategoryElementDtoChild;
import ir.blacksparrow.websitebackend.dataModel.CategoryElementEntity;
import ir.blacksparrow.websitebackend.repository.ParentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryElementRepository extends ParentRepository {
    private final ICategoryElementRepository categoryElementRepository;


    public CategoryElementRepository(ModelMapper modelMapper, ICategoryElementRepository categoryElementRepository) {
        super(modelMapper);
        this.categoryElementRepository = categoryElementRepository;

    }

    public List<CategoryElementDtoChild> findAll() {
        List<CategoryElementEntity> categoryElementEntityList = categoryElementRepository.findAll();
        return mapList(categoryElementEntityList, CategoryElementDtoChild.class);
    }

    public List<CategoryElementDtoChild> findAll(int offset, int size) {
        List<CategoryElementEntity> categoryElementEntityList = categoryElementRepository.findAll(offset, size);
        return mapList(categoryElementEntityList, CategoryElementDtoChild.class);
    }

    public Optional<CategoryElementDtoChild> getById(Long id) {
        CategoryElementEntity categoryElementEntity = categoryElementRepository.getById(id);
        return Optional.of(getModelMapper().map(categoryElementEntity, CategoryElementDtoChild.class));
    }

    public List<CategoryElementDtoChild> search(String code, String title, Long categoryId, String categoryCode, String categoryTitle) {
        List<CategoryElementEntity> categoryElementEntityList;
        if(categoryCode==null){
            categoryElementEntityList = categoryElementRepository.search(code, title, categoryId);
        }else {
            categoryElementEntityList = categoryElementRepository.search(code, title, categoryId, categoryCode, categoryTitle);
        }
        return mapList(categoryElementEntityList, CategoryElementDtoChild.class);
    }


    public List<CategoryElementDtoChild> search(String code, String title, Long categoryId, String categoryCode, String categoryTitle, int offset, int size) {
        List<CategoryElementEntity> categoryElementEntityList;
        if(categoryCode==null){
            categoryElementEntityList = categoryElementRepository.search(code, title, categoryId, offset, size);
        }else {
            categoryElementEntityList = categoryElementRepository.search(code, title, categoryId, categoryCode, categoryTitle, offset, size);
        }
        return mapList(categoryElementEntityList, CategoryElementDtoChild.class);
    }

    public Optional<CategoryElementDtoChild> insertAndUpdate(CategoryElementDtoChild categoryElementDtoChild) {
        CategoryElementEntity categoryElementEntity = getModelMapper().map(categoryElementDtoChild, CategoryElementEntity.class);
        categoryElementEntity = categoryElementRepository.save(categoryElementEntity);
        return Optional.of(getModelMapper().map(categoryElementEntity, CategoryElementDtoChild.class));
    }

    public List<CategoryElementDtoChild> insertAndUpdateAll(List<CategoryElementDtoChild> categoryElementDtoChildList) {
        List<CategoryElementEntity> categoryElementEntityList = mapList(categoryElementDtoChildList, CategoryElementEntity.class);
        categoryElementEntityList = categoryElementRepository.saveAll(categoryElementEntityList);
        return mapList(categoryElementEntityList, CategoryElementDtoChild.class);
    }

    public void delete(Long id) {
        categoryElementRepository.deleteById(id);
    }

}
