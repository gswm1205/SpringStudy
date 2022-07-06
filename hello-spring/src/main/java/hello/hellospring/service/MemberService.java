package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository= new MemoryMemberRepository();

    /**
    *회원가입
    * */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원x
        // command + option + v -> Optional<Member> result... 자동완성
        // Optional 로 한번 감싸면 그 안에 member 안에 있음 ifPresent 존재여부 확인 가능
        // null 가능성이 있으면 Optional로 한번 감싸서 해줌
        // 바로 꺼내는 건 권장하지 않으니 이걸 써라..

        /* 안 이뻐...
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        // Optional return 이기 때문에 가능
        //이런 경우는.. method로 뽑는 것이 좋음
        // ctrl + t -> Refactor
        // command + option + M -> 메소드 추출
        vaildateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw  new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 회원 전체 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
