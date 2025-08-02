package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // given
        Long memberId = 1L; // 기본타입은 null을 지원하지 않기떄문에 우선은 래퍼타입으로 지정한다.
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10_000);

        // than
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 할인 금액
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000); // 할인 금액을 뺀 원금
    }
}
