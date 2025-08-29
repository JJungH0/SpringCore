package hello.core.autowired;

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

public class AllBeanTest2 {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, PolicyService.class);
        PolicyService policyService = ac.getBean(PolicyService.class);

        Member 홍길동 = new Member(1L, "홍길동", Grade.VIP);
        int discountAmount = policyService.discount(홍길동, 10000, "fixDiscountPolicy");
        Assertions.assertThat(policyService).isInstanceOf(PolicyService.class);
        Assertions.assertThat(discountAmount).isEqualTo(1000);
    }


    static class PolicyService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        public PolicyService(Map<String, DiscountPolicy> policyMap,
                             List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;
        }

        public int discount(Member member, int amount, String code) {
            DiscountPolicy policy = policyMap.get(code);
            System.out.println(policy);
            return policy.discount(member, amount);
        }
    }
}
