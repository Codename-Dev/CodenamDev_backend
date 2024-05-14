package codeNameDev.codeNameDev.category.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInfo {
    private Long questionId;
    private String questionContent;
}
