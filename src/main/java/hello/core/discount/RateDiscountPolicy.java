package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    // 할인율 10%으로 설정
    private int discountPercent = 10;

    // 현재 메서드는 고객의 등급이 VIP경우 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            // 고객의 구매 가격에 따른 10% 할인
            return price * discountPercent / 100;
        }
        return 0;
    }
}
