package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    /**
     * 라이브러리
     * 내가 직접 작성한 코드를 직접 실행하고 있다.
     * 이것은 프레임워크가 아닌 라이브러리다.
     */
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                AppConfig.class
        );

        // memberService 라는 이름으로 등록된 빈을 MemberService.class 라는 반환타입으로 가져온다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
