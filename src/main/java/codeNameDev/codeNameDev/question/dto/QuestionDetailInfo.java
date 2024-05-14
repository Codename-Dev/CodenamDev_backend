package codeNameDev.codeNameDev.question.dto;

import codeNameDev.codeNameDev.choice.domain.*;
import codeNameDev.codeNameDev.common.entity.*;
import lombok.*;

import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDetailInfo {
    private Long questionId;
    private String questionContent;
    private String explanation;
    private Long categoryId;
    private String categoryName;
    private String correctAnswer;
    private List<ChoiceInfo> choices;
    private Status status;
}
