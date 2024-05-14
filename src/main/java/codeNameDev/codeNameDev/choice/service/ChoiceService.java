package codeNameDev.codeNameDev.choice.service;

import codeNameDev.codeNameDev.choice.domain.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChoiceService {
    @Autowired
    private final ChoiceRepository choiceRepository;
}
