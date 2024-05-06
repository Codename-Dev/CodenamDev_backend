package codeNameDev.codeNameDev.category.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListInfo {
    private Long categoryId;
    private String categoryName;
    private String categoryContent;
    private Long parentCategoryId;
    private String parentCategoryName;
}
