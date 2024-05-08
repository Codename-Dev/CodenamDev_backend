package codeNameDev.codeNameDev.question.dto;

import codeNameDev.codeNameDev.common.entity.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionListInfo {
    private Long questionId;
    private String questionContent;
    private Long categoryId;
    private String categoryName;
    private Status status;
}
