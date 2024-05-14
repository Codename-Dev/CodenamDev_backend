package codeNameDev.codeNameDev.common.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Getter
@MappedSuperclass //속성을 상속해주는 진짜 상속은 아님
public class BaseTimeEntity {

    @Column(updatable = false) //실수로 값을 바꿔도 update 되지 않음
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
