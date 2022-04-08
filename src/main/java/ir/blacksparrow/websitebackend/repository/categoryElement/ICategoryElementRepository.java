package ir.blacksparrow.websitebackend.repository.categoryElement;

import ir.blacksparrow.websitebackend.dataModel.CategoryElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryElementRepository extends JpaRepository<CategoryElementEntity, Long> {
    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY_ELEMENT" +
            " WHERE (?1 is null or CODE = ?1) " +
            " and (?2 is null or TITLE = ?2)" +
            " and (?3 is null or CATEGORY_ID = ?3)"
            , nativeQuery = true)
    List<CategoryElementEntity> search(String code, String title, long categoryId);

    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY_ELEMENT" +
            " WHERE (?1 is null or CODE = ?1) " +
            " and (?2 is null or TITLE = ?2)" +
            " and (?3 is null or CATEGORY_ID = ?3)" +
            " OFFSET ?4" +
            " LIMIT ?5"
            , nativeQuery = true)
    List<CategoryElementEntity> search(String code, String title, long categoryId, int offset, int size);

    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY_ELEMENT natural join BS_CATEGORY on BS_CATEGORY_ELEMENT.CATEGORY_ID=BS_CATEGORY.ID" +
            " WHERE (?1 is null or BS_CATEGORY_ELEMENT.CODE = ?1) " +
            " and (?2 is null or BS_CATEGORY_ELEMENT.TITLE = ?2)" +
            " and (?3 is null or BS_CATEGORY.ID = ?3)" +
            " and (?4 is null or BS_CATEGORY.CODE = ?4)" +
            " and (?5 is null or BS_CATEGORY.TITLE = ?5)"
            , nativeQuery = true)
    List<CategoryElementEntity> search(String code, String title, long categoryId, String categoryCode, String categoryTitle);

    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY_ELEMENT natural join BS_CATEGORY on BS_CATEGORY_ELEMENT.CATEGORY_ID=BS_CATEGORY.ID" +
            " WHERE (?1 is null or BS_CATEGORY_ELEMENT.CODE = ?1) " +
            " and (?2 is null or BS_CATEGORY_ELEMENT.TITLE = ?2)" +
            " and (?3 is null or BS_CATEGORY.ID = ?3)" +
            " and (?4 is null or BS_CATEGORY.CODE = ?4)" +
            " and (?5 is null or BS_CATEGORY.TITLE = ?5)" +
            " OFFSET ?6" +
            " LIMIT ?7"
            , nativeQuery = true)
    List<CategoryElementEntity> search(String code, String title, long categoryId, String categoryCode, String categoryTitle, int offset, int size);

    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY_ELEMENT" +
            " OFFSET ?1" +
            " LIMIT ?2"
            , nativeQuery = true)
    List<CategoryElementEntity> findAll(int offset, int size);
}
