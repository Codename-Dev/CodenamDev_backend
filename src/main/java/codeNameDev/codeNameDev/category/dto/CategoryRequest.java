package codeNameDev.codeNameDev.category.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String categoryName;
    private String categoryContent;
    private Long parentCategoryId;
}
