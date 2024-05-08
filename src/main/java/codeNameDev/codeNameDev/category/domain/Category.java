package codeNameDev.codeNameDev.category.domain;

import codeNameDev.codeNameDev.common.entity.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Category extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    private String categoryContent;

    @ManyToOne(fetch = FetchType.LAZY) // 부모 카테고리와의 단방향 연관관계
    @JoinColumn(name = "parent_category_id") // 외래키는 parent_category_id
    private Category parentCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    public void updateCategoryName(String newCategoryName) {
        this.categoryName = newCategoryName;
    }

    public void updateCategoryContent(String newCategoryContent) {
        this.categoryName = newCategoryContent;
    }

    public void updateParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }
}
