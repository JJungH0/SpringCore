package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    /**
     * 프레임 워크
     * 내가 작성한 코드를 제어하고, 대신 실행하면 프레임워크이다.
     * 현재 테스트코드는 Junit의 라이프사이클을 통해 실행된다.
     * 제어의 역전이 통한거다.
     */

    private MemberService memberService;

    // 각 테스트 실행전에 한번씩 실행
    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
