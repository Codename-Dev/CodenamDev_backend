package codeNameDev.codeNameDev.member.domain.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignInDto {

    @Valid
    @NotNull
    private String nickname;

    @Valid
    @NotNull
    private String password;
}
