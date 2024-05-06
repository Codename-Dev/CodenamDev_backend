package codeNameDev.codeNameDev.question.dto;

import lombok.*;

import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddQuestionRequest {
    private String questionContent;
    private String explanation;
    private String correctAnswer; // 주관식 정답
    private Long categoryId;
    private List<ChoiceRequest> choices; // 객관식 선택지 정보

    @Getter @Builder
    @AllArgsConstructor @NoArgsConstructor
    public static class ChoiceRequest {
        private String choiceContent;
        private String correctYN;
    }
}
