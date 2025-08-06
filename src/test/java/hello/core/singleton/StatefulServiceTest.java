package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        /**
         * 스프링 컨테이너에서 @Bean으로 등록된 StatefulService는 싱글톤으로 관리
         * -> 즉 ac.getBean()으로 가져온 두 객는 실제로 동인한 인스턴스
         * -> 그렇다면 공유된 필드를 사용중인것.
         * -> 그래서 마지막 호출자가 20,000원으로 order메서드를 호출하니
         * -> 값이 20,000원으로 바뀌어버린것.
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA -> A사용자 10,000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB -> B사용자 20,000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice(); // 10,000원 ?
        System.out.println("price = " + price);


        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}