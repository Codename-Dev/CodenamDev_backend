package codeNameDev.codeNameDev.category.domain;

import codeNameDev.codeNameDev.category.dto.*;
import codeNameDev.codeNameDev.question.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    Optional<Category> findByCategoryName(String categoryName);

/*    // 부모 카테고리의 ID와 이름을 조회하는 쿼리 메소드 -> 추후 성능 개선 검토시 사용
    @Query("SELECT new codeNameDev.codeNameDev.caegory.domain.dto.ParentCategoryDto(c.parent.id, c.parent.categoryName) FROM Category c WHERE c.id = :categoryId")
    Optional<ParentCategoryInfo> findParentCategoryInfoById(@Param("categoryId") Long categoryId);

    Optional<List<Question>> findAllQuestionsByCategoryId(@Param("categoryId") Long categoryId);*/
}
