package codeNameDev.codeNameDev.member.domain.dto;

import codeNameDev.codeNameDev.common.entity.Status;
import codeNameDev.codeNameDev.member.domain.Member;
import codeNameDev.codeNameDev.member.domain.Role;
import codeNameDev.codeNameDev.member.domain.SocialType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

    private String nickname;

    private String email;

    private String password;

    private SocialType socialType;

    private Role role;

    private Status status = Status.ACTIVE;


    @Builder
    public MemberDto(String nickname, String email, String password, SocialType socialType, Role role, Status status) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.socialType = socialType;
        this.role = role;
        this.status = status;
    }

    public MemberDto(Member entity){
        this.nickname = entity.getNickname();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.socialType = entity.getSocialType();
        this.role = entity.getRole();
        this.status = entity.getStatus();

    }
}
