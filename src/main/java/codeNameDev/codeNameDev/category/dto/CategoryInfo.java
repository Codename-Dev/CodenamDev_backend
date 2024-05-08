package codeNameDev.codeNameDev.category.dto;

import codeNameDev.codeNameDev.category.domain.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInfo {
    private Long categoryId;
    private String categoryName;
    private String categoryContent;
    private Category parentCategory;
}
