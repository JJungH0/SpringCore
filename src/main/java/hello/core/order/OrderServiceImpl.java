package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// DIP원칙 준수 -> 추상화에만 의존함

/**
 * 설계 변경으로 "OrderServiceImpl"은 "FixDiscount || RateDiscount"를 의존하지 않는다.
 * 단지 "DiscountPolicy" 인터페이스에만 의존한다.
 * "OrderServiceImpl" 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(=주입될지)는 알 수 없다.
 * "OrderServiceImpl"의 생성자를 통해 어떤 구현 객체를 주입할지는 오직 외부("AppConfig")에서 결정
 * "OrderServiceImpl"은 이제부터 실행에만 집중하면 된다.
 */

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // AppConfig을 통해 값을 주입받음.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원 찾기.
        Member member = memberRepository.findById(memberId);
        // 2. 회원의 등급으로 인한 할인율 찾기.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 3. 주문서 리턴
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;

    }
}
