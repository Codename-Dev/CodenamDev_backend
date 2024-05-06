package codeNameDev.codeNameDev.question.domain;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();
    Optional<Question> findById(Long Id);

}
