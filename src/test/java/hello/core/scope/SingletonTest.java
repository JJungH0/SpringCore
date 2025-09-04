package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

@Slf4j
class SingletonTest {

    @Test
    void singletonBeanFind() {
        /**
         * 스프링 컨테이너에서 관리하는 싱글톤 보장 빈을 반환함. (= 즉 같은 참조객체)
         * 1. 빈 초기화 메서드를 실행하고
         * 2. 같은 인스턴스 빈을 조회하고
         * 3. 종료 메서드까지 정상 호출됨
         */
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("조회 시점");
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isSameAs(bean2);

        ac.close();
    }
    @Scope("singleton")
    static class SingletonBean {

        /**
         * PostConstruct :
         * DI 끝난 직후 실행
         */
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        /**
         * PreDestroy :
         * 컨테이너 종료 직전 실행
         */
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");

        }
    }
}
