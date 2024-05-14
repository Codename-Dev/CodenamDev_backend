package codeNameDev.codeNameDev.choice.domain;

import codeNameDev.codeNameDev.common.entity.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Choice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String choiceContent;

    @Column(nullable = false)
    private String correctYN;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    public void updateStatus(Status status) {
        this.status = status;
    }
}