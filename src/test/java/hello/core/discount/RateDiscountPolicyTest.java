package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();


    /**
     * 성공 테스트 케이스
     */
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // given
        Member member = new Member(1L, "최정환", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 20000);

        // then
        assertThat(discount).isEqualTo(2000);
    }

    /**
     * 실패 테스트 케이스
     */
    @Test
    @DisplayName("VIP가 아니라면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        // given
        Member member = new Member(2L, "홍길동", Grade.BASIC);

        // when
        int discount = discountPolicy.discount(member, 20000);

        // then
        assertThat(discount).isZero(); // 밑 메서드와 같은 기능
        assertThat(discount).isEqualTo(0);
    }
}
