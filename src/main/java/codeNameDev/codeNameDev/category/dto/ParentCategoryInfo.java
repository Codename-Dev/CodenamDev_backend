package codeNameDev.codeNameDev.category.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentCategoryInfo {
    private Long parentCategoryId;
    private String parentCategoryName;
}
