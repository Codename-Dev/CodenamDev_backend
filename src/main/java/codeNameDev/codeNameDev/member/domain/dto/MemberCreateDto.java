package codeNameDev.codeNameDev.member.domain.dto;


import codeNameDev.codeNameDev.common.entity.Status;
import codeNameDev.codeNameDev.member.domain.Role;
import codeNameDev.codeNameDev.member.domain.SocialType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateDto {
    private String nickname;

    private String email;

    private String password;

    private SocialType socialType;

}
