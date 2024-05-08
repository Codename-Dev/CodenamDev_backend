package codeNameDev.codeNameDev.question.service;

import codeNameDev.codeNameDev.category.domain.*;
import codeNameDev.codeNameDev.choice.domain.*;
import codeNameDev.codeNameDev.common.entity.*;
import codeNameDev.codeNameDev.common.exception.*;
import codeNameDev.codeNameDev.question.domain.*;
import codeNameDev.codeNameDev.question.dto.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final CategoryQuestionRepository categoryQuestionRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ChoiceRepository choiceRepository;

    /**
     * 전체 문제 목록 조회
     * **/
    public List<QuestionListInfo> getAllQuestions() {
        try {
            List<QuestionListInfo> questionListInfos = new ArrayList<>();
            List<Question> questions = questionRepository.findAll();
            if (questions.isEmpty()) {
                throw new CustomException(HttpStatus.NOT_FOUND, "문제가 존재하지 않습니다. 문제를 등록해주세요.");
            }

            for (Question question : questions) {
                Optional<CategoryQuestion> categoryQuestion = categoryQuestionRepository.findByQuestionId(question.getId());
                Category category = categoryQuestion.get().getCategory();

                QuestionListInfo questionListInfo = QuestionListInfo.builder()
                        .questionId(question.getId())
                        .questionContent(question.getQuestionContent())
                        .categoryId(category.getId())
                        .categoryName(category.getCategoryName())
                        .status(question.getStatus())
                        .build();
                questionListInfos.add(questionListInfo);
            }

            return questionListInfos;

        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "문제 목록을 받아오지 못했습니다.");
        }
    }

    /**
     * 특정 문제 상세 정보 조회
     * **/
    public QuestionDetailInfo getQuestionDetail(Long questionId) {
        try {
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "조회할 문제를 찾을 수 없습니다."));

            // 문제가 속한 카테고리
            Optional<CategoryQuestion> existingCategoryQuestion = categoryQuestionRepository.findByQuestionId(question.getId());
            if (existingCategoryQuestion.isPresent()) {
                Category category = existingCategoryQuestion.get().getCategory();
            }

            // 문제가 갖고 있는 선택지
            List<ChoiceInfo> choiceInfos = question.getChoices()
                    .stream()
                    .filter(choice -> choice != null) // question 객체가 null이 아닌 경우만 처리
                    .map(choice -> ChoiceInfo.builder()
                            .choiceId(choice.getId())
                            .choiceContent(choice.getChoiceContent())
                            .correctYN(choice.getCorrectYN())
                            .build())
                    .collect(Collectors.toList());

            QuestionDetailInfo questionDetailInfo = QuestionDetailInfo.builder()
                    .questionId(question.getId())
                    .questionContent(question.getQuestionContent())
                    .explanation(question.getExplanation())
                    .choices(choiceInfos)
                    .correctAnswer(question.getCorrectAnswer())
                    .categoryId(existingCategoryQuestion.get().getCategory().getId())
                    .categoryName(existingCategoryQuestion.get().getCategory().getCategoryName())
                    .status(question.getStatus())
                    .build();

            return questionDetailInfo;

        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "문제 상세 조회에 실패하였습니다.");
        }
    }

    /**
     * 신규 문제 생성: choice의 정보도 함께 생성한다.
     * **/
    @Transactional
    public void addQuestion(AddQuestionRequest addQuestionRequest) {
        try {

            // 각 Choice에 대해 반복하여 Question 엔티티에 추가
            List<Choice> choices = addQuestionRequest.getChoices().stream()
                    .map(choiceRequest -> Choice.builder()
                            .choiceContent(choiceRequest.getChoiceContent())
                            .correctYN(choiceRequest.getCorrectYN())
                            .status(Status.ACTIVE)
                            .build())
                    .collect(Collectors.toList());

            Question newQuestion = Question.builder()
                    .questionContent(addQuestionRequest.getQuestionContent())
                    .explanation(addQuestionRequest.getExplanation())
                    .correctAnswer(addQuestionRequest.getCorrectAnswer())
                    .choices(choices)
                    .status(Status.ACTIVE)
                    .build();
/*
            // Question 엔티티에 Choices 추가
            choices.forEach(newQuestion::addChoice);
*/

            // 변경된 Question 엔티티 저장
            questionRepository.save(newQuestion);

            // CategoryQuestion에도 정보 저장
            Category category = categoryRepository.findById(addQuestionRequest.getCategoryId())
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다."));

            CategoryQuestion newCategoryQuestion = CategoryQuestion.builder()
                    .category(category)
                    .question(newQuestion)
                    .build();
            categoryQuestionRepository.save(newCategoryQuestion);


        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "문제 및 선택지 생성에 실패하였습니다.");
        }
    }

    /**
     * 특정 문제 수정
     * **/


    /**
     * 특정 문제 삭제
     * **/

}
