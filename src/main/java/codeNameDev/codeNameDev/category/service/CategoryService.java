package codeNameDev.codeNameDev.category.service;

import codeNameDev.codeNameDev.category.domain.*;
import codeNameDev.codeNameDev.category.dto.*;
import codeNameDev.codeNameDev.choice.domain.*;
import codeNameDev.codeNameDev.common.entity.*;
import codeNameDev.codeNameDev.common.exception.*;
import codeNameDev.codeNameDev.question.domain.*;
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
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final CategoryQuestionRepository categoryQuestionRepository;
    @Autowired
    private final QuestionRepository questionRepository;
    @Autowired
    private final ChoiceRepository choiceRepository;

    /**
     * 전체 카테고리 목록 조회
     **/
    public List<CategoryListInfo> getAllCategories() {
        try {
            List<CategoryListInfo> categoryListInfos = new ArrayList<>();
            List<Category> categories = categoryRepository.findAll();
            if (categories.isEmpty()) {
                throw new CustomException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다. 카테고리를 등록해주세요.");
            }

            for (Category category : categories) {
                Long parentCategoryId = null;
                String parentCategoryName = null;
                if (category.getParentCategory() != null) { // 부모 카테고리가 null이 아닌 경우
                    parentCategoryId = category.getParentCategory().getId();
                    parentCategoryName = category.getParentCategory().getCategoryName();
                }

                CategoryListInfo categoryListInfo = CategoryListInfo.builder()
                        .categoryId(category.getId())
                        .categoryName(category.getCategoryName())
                        .categoryContent(category.getCategoryContent())
                        .parentCategoryId(parentCategoryId)
                        .parentCategoryName(parentCategoryName)
                        .build();
                categoryListInfos.add(categoryListInfo);
            }
            return categoryListInfos;
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 목록을 받아오지 못했습니다.");
        }
    }

    /**
     * 특정 카테고리에 속한 상세 정보 및 모든 문제 조회: 문제의 id, content 포함
     * **/
    public CategoryDetailInfo getCategoryDetail(Long categoryId) {
        try {
            // 카테고리 정보 조회
            Category existingCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "조회할 카테고리를 찾을 수 없습니다."));

            // 카테고리에 속한 질문들 조회
            List<QuestionInfo> questionInfos = categoryQuestionRepository.findByCategoryId(categoryId)
                    .orElse(Collections.emptyList()) // Optional이 비어있으면 빈 리스트 반환
                    .stream()
                    .filter(categoryQuestion -> categoryQuestion.getQuestion() != null) // question 객체가 null이 아닌 경우만 처리
                    .map(categoryQuestion -> QuestionInfo.builder()
                            .questionId(categoryQuestion.getQuestion().getId())
                            .questionContent(categoryQuestion.getQuestion().getQuestionContent())
                            .build())
                    .collect(Collectors.toList());


            // 카테고리 상세 정보 객체 생성
            CategoryDetailInfo categoryDetailInfo = CategoryDetailInfo.builder()
                    .categoryId(existingCategory.getId())
                    .categoryName(existingCategory.getCategoryName())
                    .categoryContent(existingCategory.getCategoryContent()) // categoryContent 추가
                    .parentCategoryId(existingCategory.getParentCategory() != null ? existingCategory.getParentCategory().getId() : null)
                    .parentCategoryName(existingCategory.getParentCategory() != null ? existingCategory.getParentCategory().getCategoryName() : null)
                    .questions(questionInfos)
                    .build();

            return categoryDetailInfo;
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 상세 조회에 실패하였습니다.");
        }
    }

    /**
     * 신규 카테고리 생성
     * **/
    @Transactional
    public CategoryInfo addCategory(String categoryName, String categoryContent, Long parentCategoryId) {
        try {
            Category parentCategory = null;
            if (parentCategoryId != null) {
                parentCategory = categoryRepository.findById(parentCategoryId).orElse(null);
            }

            Category category = Category.builder()
                    .categoryName(categoryName)
                    .categoryContent(categoryContent)
                    .parentCategory(parentCategory)
                    .build();
            categoryRepository.save(category);

            CategoryInfo categoryInfo = CategoryInfo.builder()
                    .categoryId(category.getId())
                    .categoryName(category.getCategoryName())
                    .categoryContent(category.getCategoryContent())
                    .parentCategory(category.getParentCategory())
                    .build();

            return categoryInfo;

        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 생성에 실패하였습니다.");
        }
    }

    /**
     * 특정 카테고리 수정
     * **/
    @Transactional
    public CategoryInfo updateById(Long categoryId, String categoryName, String categoryContent, Long parentCategoryId) {
        try {
            // 수정할 카테고리
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "수정할 카테고리를 찾을 수 없습니다."));

            if(categoryName == null && categoryContent == null && parentCategoryId == null) {
                throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 수정 값을 입력해주세요.");
            }

            if(categoryName != null) {
                category.updateCategoryName(categoryName);
            }

            if(categoryContent != null) {
                category.updateCategoryContent(categoryContent);
            }

            if(parentCategoryId != null) {
                Category parentCategory = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "부모 카테고리를 찾을 수 없습니다."));
                category.updateParentCategory(parentCategory);
            }

            categoryRepository.save(category);

            CategoryInfo categoryInfo = CategoryInfo.builder()
                    .categoryId(category.getId())
                    .categoryName(category.getCategoryName())
                    .categoryContent(category.getCategoryContent())
                    .parentCategory(category.getParentCategory())
                    .build();

            return categoryInfo;

        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 수정에 실패하였습니다.");
        }
    }

    /**
     * 특정 카테고리 삭제 -> 수정 필요
     * **/

    @Transactional
    public CategoryInfo deleteCategory(Long categoryId) {
        try {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "삭제할 카테고리를 찾을 수 없습니다."));

            category.updateStatus(Status.INACTIVE);
            categoryRepository.save(category);

            // 해당 카테고리에 속한 모든 문제도 INACTIVE
            Optional<List<CategoryQuestion>> categoryQuestions = categoryQuestionRepository.findByCategoryId(categoryId);
            if(categoryQuestions.isPresent()) {
                List<CategoryQuestion> existingCategoryQuestions = categoryQuestions.get();
                // 카테고리-문제 중간 테이블 데이터 INACTIVE
                for (CategoryQuestion existingCategoryQuestion : existingCategoryQuestions) {
                    existingCategoryQuestion.updateStatus(Status.INACTIVE);
                    categoryQuestionRepository.save(existingCategoryQuestion);
                    /**
                     * 한 문제를 여러 카테고리에서 사용한다면 하단 코드 삭제
                     * **/
                    // question의 상태도 INACTIVE로 변경
                    Question question = existingCategoryQuestion.getQuestion();
                    question.updateStatus(Status.INACTIVE);
                    // question에 속한 choice를 INACTIVE로 변경
                    List<Choice> choices = question.getChoices();
                    for (Choice choice : choices) {
                        choice.updateStatus(Status.INACTIVE);
                        choiceRepository.save(choice);
                    }
                    questionRepository.save(question);
                }
            }

            CategoryInfo categoryInfo = CategoryInfo.builder()
                    .categoryId(category.getId())
                    .categoryName(category.getCategoryName())
                    .categoryContent(category.getCategoryContent())
                    .parentCategory(category.getParentCategory())
                    .build();

            return categoryInfo;

        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "카테고리 삭제에 실패하였습니다.");
        }
    }

    /**
     * 기타
     * **/
}
