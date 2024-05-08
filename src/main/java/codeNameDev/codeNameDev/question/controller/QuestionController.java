package codeNameDev.codeNameDev.question.controller;

import codeNameDev.codeNameDev.category.dto.*;
import codeNameDev.codeNameDev.common.*;
import codeNameDev.codeNameDev.question.dto.*;
import codeNameDev.codeNameDev.question.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private final QuestionService questionService;

    /**
     * 전체 문제 목록 조회
     * **/
    @GetMapping("")
    public CommonResponse<List<QuestionListInfo>> getAllQuestions() {
        List<QuestionListInfo> questionListInfos = questionService.getAllQuestions();
        return CommonResponse.success("전체 문제 목록 조회에 성공하였습니다.", questionListInfos);
    }

    /**
     * 특정 문제 상세 정보 조회
     * **/
    @GetMapping("/{questionId}")
    public CommonResponse<QuestionDetailInfo> getQuestionDetail(@PathVariable("questionId") Long questionId) {
        QuestionDetailInfo questionDetailInfo = questionService.getQuestionDetail(questionId);
        return CommonResponse.success("문제 상세 정보 조회에 성공하였습니다.", questionDetailInfo);
    }

    /**
     * 신규 문제 생성: choice의 정보도 함께 생성한다.
     * **/
    @PostMapping("")
    public CommonResponse addQuestion(@RequestBody AddQuestionRequest addQuestionRequest) {
        questionService.addQuestion(addQuestionRequest);
        return CommonResponse.success("문제 생성에 성공하였습니다.");
    }

    /**
     * 특정 문제 수정
     * **/


    /**
     * 특정 문제 삭제
     * **/


}
