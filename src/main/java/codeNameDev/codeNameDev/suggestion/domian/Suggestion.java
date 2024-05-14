package codeNameDev.codeNameDev.suggestion.domian;

import codeNameDev.codeNameDev.choice.domain.*;
import codeNameDev.codeNameDev.common.entity.*;
import codeNameDev.codeNameDev.member.domain.*;
import codeNameDev.codeNameDev.question.domain.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Suggestion extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suggestion_id")
    private Long id;

    @Column(name = "suggestion_title")
    private String suggestionTitle;

    @Column(name = "suggestion_content", columnDefinition = "TEXT")
    private String suggestionContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "suggestion_status", nullable = false)
    private SuggestionStatus suggestionStatus = SuggestionStatus.UNDER_REVIEW;

    @Column(name = "suggestion_date")
    private LocalDateTime suggestionDate;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "choice_id")
    private Choice choice;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;
}
