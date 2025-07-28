package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given (= 어떠한 환경이 주어졌을때)
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when (= 해당 동작을 하면)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // than (= 이런 결과가 나온다)

        // import org.assertj.core.api.Assertions;
        // findMember 같냐?
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
