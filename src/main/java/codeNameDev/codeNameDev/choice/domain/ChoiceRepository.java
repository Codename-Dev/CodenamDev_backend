package codeNameDev.codeNameDev.choice.domain;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findAll();
}
