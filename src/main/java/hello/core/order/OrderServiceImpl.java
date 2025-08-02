package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    // 현재 DIP 구조를 위반하고 있다.
    // 현재 코드상으로는 추상화 DiscountPolicy에도 의존하고 || RateDiscountPolicy라는 구현체에도 의존한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 정책 할인 구현체로 변경
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 현재 코드는 인터페이스에만 의존함 -> DIP 원칙 준수
    /**
     * 그렇지만 현재 코드의 문제점
     * -> 구현체가 없으니 당연히 NullPointException이 터짐
     * -> 그렇다면 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신
     * -> 생성하고 주입을해주어야 한다.
     */
    private DiscountPolicy discountPolicy;


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
