package Hello.spring_1.service;

import Hello.spring_1.domain.Member;
import Hello.spring_1.repository.MemberRepository;
import Hello.spring_1.repository.MemoryMemberRepository;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);

    }

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void 회원_가입() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        //when
        long saveId = memberService.join(member1);

        //then
        Member member2 = memberService.findOnes(saveId).get();
        assertThat(member1.getName()).isEqualTo(member2.getName());
    }

    @Test
    public void 중복_확인(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOnes() {
    }
}