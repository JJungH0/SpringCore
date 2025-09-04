package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class PrototypeTest {
    /**
     * 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행.
     * 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고 초기화
     * 프로토타입 빈을 2번 조회했으므로 완전히 다른 스프링 빈이 생성되고, 초기화도 2번 실행됨
     * 싱글톤 빈은 스프링 컨테이너가 관리하기 때문에 스프링 컨테이너가 종료될 때의 빈의 종료 메서드가 실행됨
     * 프로토타입 빈은 스프링 컨테이너가 생성과 의존관계 주입 그리고 초기화 까지만 관여하고, 더는 관리 X
     * 따라서 프로토타입 빈은 스프링 컨테이너가 종료될 때 @PreDestroy 같은 종료 메서드가 전혀 실행되지 않음.
     */
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find bean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find bean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("Prototype.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destroy");
        }
    }
}
