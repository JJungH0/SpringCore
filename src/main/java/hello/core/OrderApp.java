package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        // AppConfig을 사용해서 실행

        AppConfig appConfig = new AppConfig();

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImpl(null,null);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member); // 메모리 객체에 삽입

        Order order = orderService.createOrder(memberId, "itemA", 20000);
        
        System.out.println("order = " + order);
    }
}
