package codeNameDev.codeNameDev.member;


import codeNameDev.codeNameDev.common.CommonResponse;
import codeNameDev.codeNameDev.common.exception.ErrorResponse;
import codeNameDev.codeNameDev.member.domain.Member;
import codeNameDev.codeNameDev.member.domain.MemberService;
import codeNameDev.codeNameDev.member.domain.dto.MemberCreateDto;
import codeNameDev.codeNameDev.member.domain.dto.MemberDto;
import codeNameDev.codeNameDev.member.domain.dto.MemberSignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class MemberController {

    private final MemberService memberService;


    //회원가입
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody MemberCreateDto dto){
        Optional<Member> member = memberService.addMember(dto);

        if(member.isEmpty()){
            ErrorResponse error = new ErrorResponse("회원가입 실패");
            return ResponseEntity.ok(error);
        }

        CommonResponse response = CommonResponse.success("회원가입 성공", true);
        return ResponseEntity.ok(response);
    }


    //로그인
    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody MemberSignInDto dto){
        Optional<MemberDto> optional = memberService.signIn(dto);
        if(optional.isEmpty()){
            ErrorResponse error = new ErrorResponse("로그인 실패");
            return ResponseEntity.ok(error);
        }

        CommonResponse response = CommonResponse.success("로그인 성공", true);
        return ResponseEntity.ok(response);
    }
}
