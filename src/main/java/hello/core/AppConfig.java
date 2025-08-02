package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
 * AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 생성자를 통해서 주입(연결) 해준다.
 */
@Configuration
public class AppConfig {

    /**
     * 사용자는 AppConfig 객체의 memberService()라는 메서드를 사용시
     * 새로운 구현체의 할당을 MemoryMemberRepository()로 할당한다.
     * 생성자 주입방식.
     */

    /**
     * 바로 new로 등록하는게 아닌 메서드로 분리하여
     * 역할과 구현을 잘 보이게 만들었다.
     * command + option + m -> 메서드 추출
     */
    // 빈으로 등록될때 메서드 이름으로 등록된다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    private static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 생성자 주입 방식.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * OCP 원칙 준수
     */
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy(); // 정량 할인
        return new RateDiscountPolicy(); // 정액 할인
    }

}
