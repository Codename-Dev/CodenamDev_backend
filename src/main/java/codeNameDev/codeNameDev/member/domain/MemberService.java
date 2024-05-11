package codeNameDev.codeNameDev.member.domain;


import codeNameDev.codeNameDev.member.domain.dto.MemberCreateDto;
import codeNameDev.codeNameDev.member.domain.dto.MemberDto;
import codeNameDev.codeNameDev.member.domain.dto.MemberSignInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원가입
    @Transactional
    public Optional<Member> addMember(MemberCreateDto dto){

        Member member = Member.makeMember(dto);
        Member result = memberRepository.save(member);

        return Optional.ofNullable(result);
    }

    //로그인
    @Transactional(readOnly = true)
    public Optional<MemberDto> signIn(MemberSignInDto dto){
        String nickName = dto.getNickname();

        //닉네임 일치 확인
        Optional<Member> found = memberRepository.findByNickname(nickName);
        if(found.isEmpty()) return Optional.empty();

        Member member = found.get();

        //비밀번호 일치확인
        if(!member.getPassword().equals(dto.getPassword())){
            return Optional.empty();
        }

        return Optional.ofNullable(new MemberDto(member));
    }
}
