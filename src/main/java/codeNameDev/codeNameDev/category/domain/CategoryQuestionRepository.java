package codeNameDev.codeNameDev.category.domain;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CategoryQuestionRepository extends JpaRepository<CategoryQuestion, Long> {

    Optional<CategoryQuestion> findByCategoryIdAndQuestionId(Long categoryId, Long questionId);

    Optional<List<CategoryQuestion>> findByCategoryId(Long categoryId);

    Optional<CategoryQuestion> findByQuestionId(Long questionId);
}
