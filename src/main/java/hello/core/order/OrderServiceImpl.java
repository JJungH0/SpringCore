package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 찾기.
        Member member = memberRepository.findById(memberId);
        // 2. 회원의 등급으로 인한 할인율 찾기.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 3. 주문서 리턴
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
