package hello.core.autowired;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member userA = new Member(1L, "userA", Grade.VIP);
        int discountPolicy = discountService.discount(userA, 10000, "rateDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPolicy).isEqualTo(1000);
    }


    static class DiscountService{
        /**
         * @policyMap :
         * -> DiscountPolicy를 주입받는다.
         * -> 이때 구현체인 fixDiscountPolicy,와 rateDiscountPolicy가 주입된다.
         * @discount() :
         * -> discountCode로 "fixDiscountPolicy"가 넘어오면 map에서 fixDiscountPolicy 스프링 빈을 찾아서 실행
         * -> 반대로 "rateDiscountPolicy"가 넘어온다면 rateDiscountPolicy 빈을 찾아서 실행
         * @주입분석 :
         * -> Map<String, DiscountPolicy>
         *     map의 키에 스프링 빈의 이름을 넣어주고,
         *     해당 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아줌
         * -> List<DiscountPolicy>
         *     DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
         * -> 만약 해당하는 타입의 스프링빈이 존재하지 않다면, 빈 컬렉션이나 Map를 주입
         */
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        /**
         * DiscountService(Map, List)를 생성자 주입으로 받음.
         * -> Map의 Key(=빈 이름), Value(= 해당 객체), List(= 모든 정책의 목록)
         */
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;
            System.out.println("policyMap = " + policyMap);
            System.out.println("polices = " + polices);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }
}
