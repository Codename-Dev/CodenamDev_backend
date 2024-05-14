package codeNameDev.codeNameDev.category.controller;

import codeNameDev.codeNameDev.category.domain.*;
import codeNameDev.codeNameDev.category.dto.*;
import codeNameDev.codeNameDev.category.service.*;
import codeNameDev.codeNameDev.common.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    /**
     * 전체 카테고리 목록 조회
     **/
    @GetMapping("")
    public CommonResponse<List<CategoryListInfo>> getAllCategories() {
        List<CategoryListInfo> categoryListInfos = categoryService.getAllCategories();
        return CommonResponse.success("전체 카테고리 목록 조회에 성공하였습니다.", categoryListInfos);
    }

    /**
     * 특정 카테고리 상세 정보 조회
     * **/
    @GetMapping("/{categoryId}")
    public CommonResponse<CategoryDetailInfo> getCategoryDetail(@PathVariable("categoryId") Long categoryId) {
        CategoryDetailInfo categoryDetailInfo = categoryService.getCategoryDetail(categoryId);
        return CommonResponse.success("카테고리 상세 정보 조회에 성공하였습니다.", categoryDetailInfo);
    }

    /**
     * 신규 카테고리 생성
     * **/
    @PostMapping("")
    public CommonResponse<CategoryInfo> addCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryInfo newCategoryInfo = categoryService.addCategory(categoryRequest.getCategoryName(),
                categoryRequest.getCategoryContent(),
                categoryRequest.getParentCategoryId());
        return CommonResponse.success("카테고리 생성에 성공하였습니다.", newCategoryInfo);
    }

    /**
     * 특정 카테고리 수정
     * **/
    @PatchMapping("/{categoryId}")
    public CommonResponse<CategoryInfo> updateCategory(@PathVariable("categoryId") Long categoryId,
            @RequestBody CategoryRequest categoryRequest) {
        CategoryInfo updatedCategoryInfo = categoryService.updateById(categoryId, categoryRequest.getCategoryName(),
                categoryRequest.getCategoryContent(), categoryRequest.getParentCategoryId());

        return CommonResponse.success("카테고리 수정에 성공하였습니다.", updatedCategoryInfo);
    }

    /**
     * 특정 카테고리 삭제
     * **/
    @PatchMapping("/{categoryId}/delete")
    public CommonResponse<CategoryInfo> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryInfo categoryInfo = categoryService.deleteCategory(categoryId);

        return CommonResponse.success("카테고리 삭제에 성공하였습니다.", categoryInfo);
    }

}
