package codeNameDev.codeNameDev.question.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceInfo {
    private Long choiceId;
    private String choiceContent;
    private String correctYN;
}
