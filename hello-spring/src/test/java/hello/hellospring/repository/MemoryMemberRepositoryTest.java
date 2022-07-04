package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //검증 : 저장한 member 와 DB에 저장된 것과 완전히 똑같으면 참
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result); // expect, actual
        assertThat(member).isEqualTo(result); //요즘 거, option+Enter 하면 Assertions 사라짐, static import
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift + F6 복사 변수 이름 같이 바뀜
        member2.setName("spring2");
        repository.save(member2);

        Member result  = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        // 2번 돌리면 findByName 이 이상함
        /*
         findAll 이든 findByName 이든 실행 순서는 보장되지 않음
         모든 테스트는 순서와 상관없이 모든 메서드를 돌림
        */
    }
}
