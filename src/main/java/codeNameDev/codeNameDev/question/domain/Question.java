package codeNameDev.codeNameDev.question.domain;

import codeNameDev.codeNameDev.choice.domain.*;
import codeNameDev.codeNameDev.common.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionContent;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @OneToMany(cascade = CascadeType.ALL)
//    @Singular("choice")
    @JoinColumn(name = "question_id")
    private List<Choice> choices = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String correctAnswer;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    public void updateStatus(Status status) {
        this.status = status;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }
}
