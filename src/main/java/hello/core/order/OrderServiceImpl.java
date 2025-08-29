package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
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
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    /**
     * 필드 주입 방식
     */
//    @Autowired private  MemberRepository memberRepository;
//    @Autowired private  DiscountPolicy discountPolicy;
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 일반 메서드 주입 방식
     */
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//
//    }

//    /**
//     * 수정자 방식
//     */
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

    // AppConfig을 통해 값을 주입받음.
    // 만약 생성자가 1개라면 @Autowired가 기본적으로 붙어있음 (= 스프링 빈 환경에서)
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


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
