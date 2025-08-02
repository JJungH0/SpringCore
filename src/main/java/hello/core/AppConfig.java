package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
 * AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입(연결) 해준다.
 */
public class AppConfig {

    /**
     * 사용자는 AppConfig 객체의 memberService()라는 메서드를 사용시
     * 새로운 구현체의 할당을 MemoryMemberREspository()로 할당한다.
     * 생성자 주입방식.
     */

    /**
     * 바로 new로 등록하는게 아닌 메서드로 분리하여
     * 역할과 구현을 잘 보이게 만들었다.
     * command + option + m -> 메서드 추출
     */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 생성자 주입 방식.
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

}
