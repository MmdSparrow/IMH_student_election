package ir.blacksparrow.websitebackend.repository.category;

import ir.blacksparrow.websitebackend.dataModel.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY" +
            " WHERE (?1 is null or CODE = ?1) " +
            " and (?2 is null or TITLE = ?2)"
            , nativeQuery = true)
    List<CategoryEntity> search(String code, String title);

    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY" +
            " WHERE (?1 is null or CODE = ?1) " +
            " and (?2 is null or TITLE = ?2)" +
            " OFFSET ?3" +
            " LIMIT ?4"
            , nativeQuery = true)
    List<CategoryEntity> search(String code, String title, int offset, int size);

    @Query(value = "SELECT *" +
            " FROM BS_CATEGORY" +
            " OFFSET ?1" +
            " LIMIT ?2"
            , nativeQuery = true)
    List<CategoryEntity> findAll(int offset, int size);
}
