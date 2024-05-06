package codeNameDev.codeNameDev.category.domain;

import codeNameDev.codeNameDev.common.entity.*;
import codeNameDev.codeNameDev.question.domain.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Question question;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    public void updateStatus(Status status) {
        this.status = status;
    }
}
