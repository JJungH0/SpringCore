package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        /**
         * 스프링 컨테이너 생성 -> 설정 정보(AppConfig.class)를 기반으로 생성
         * ApplicationContext 는 스프링 컨테이너(Bean 공장) 역할을 함
         * AnnotationConfigApplicationContext 는 @Configuration 이 붙은 자바 기반 설정 클래스(AppConfig)
         * 를 읽어서 그 안의 @Bean 메서드들을 실행하여 객체(Bean)를 만들고, 등록하고, 관리를 해줌
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        /**
         * Bean 꺼내오기 -> 이름(memberService)과 타입(MemberService.class)로 요청
         * getBean("beanName", BeanType.class)를 사용해서 스프링이 관리하는 객체를 꺼내옴
         * 여기서 "memberService"는 AppConfig 클래스의 메서드 이름과 동일해야 함
         * 반환 타입인 MemberService.class는 형변환을 안전하게 하기 위한 타입 정보임
         * @@스프링은 내부적으로 AppConfig.memberService()를 실행한 결과 객체를 컨테이너에 넣고, 그 이름을
         * memberService로 등록해둠.
         */
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); // 메모리 객체에 삽입

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        System.out.println("order = " + order);
    }
}
