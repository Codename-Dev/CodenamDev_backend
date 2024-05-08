package codeNameDev.codeNameDev.choice.controller;

import codeNameDev.codeNameDev.choice.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/choices")
public class ChoiceController {
    @Autowired
    private final ChoiceService choiceService;

    /**
     * 전체 선택지 목록 조회
     * **/


    /**
     * 특정 선택지 상세 정보 조회
     * **/


    /**
     * 신규 선택지 생성: question 생성 시 함께 생성한다. (추후 변동 가능)
     * **/


    /**
     * 특정 선택지 수정
     * **/


    /**
     * 특정 선택지 삭제
     * **/

}
