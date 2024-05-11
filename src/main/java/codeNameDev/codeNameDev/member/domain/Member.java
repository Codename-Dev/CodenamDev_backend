package codeNameDev.codeNameDev.member.domain;

import codeNameDev.codeNameDev.common.entity.*;
import codeNameDev.codeNameDev.member.domain.dto.MemberCreateDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 60)
    private String nickname;

    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;


    public static Member makeMember(MemberCreateDto dto){
        return Member.builder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.USER)
                .socialType(dto.getSocialType())
                .status(Status.ACTIVE)
                .build();
    }

    public static Member makeAdminMember(MemberCreateDto dto){
        return Member.builder()
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.ADMIN)
                .socialType(dto.getSocialType())
                .status(Status.ACTIVE)
                .build();
    }
}
