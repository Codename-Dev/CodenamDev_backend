package codeNameDev.codeNameDev.category.dto;

import lombok.*;
import java.util.*;
import codeNameDev.codeNameDev.category.dto.QuestionInfo;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetailInfo {
    private Long categoryId;
    private String categoryName;
    private String categoryContent;
    private Long parentCategoryId;
    private String parentCategoryName;
    private List<QuestionInfo> questions;
}
