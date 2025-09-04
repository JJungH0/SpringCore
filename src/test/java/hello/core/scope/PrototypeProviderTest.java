package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import java.util.ServiceLoader;

import static org.assertj.core.api.Assertions.*;

class PrototypeProviderTest {
    @Test
    void providerTest() {
        /**
         * new Annotation..(...) 처럼 클래스들을 생성자 인자로 직접 넘기면
         * 스프링이 해당 클새들을 명시적으로 빈 정의로 등록함.
         * 냅적으로 ac.register(..) && ac.refresh();와 동일
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }



    static class ClientBean{
//        @Autowired
//        private ApplicationContext ac;

//        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;

        @Autowired
        private Provider<PrototypeBean> provider;

        public int logic() {
            /**
             * 프로토타입 스코프의 동작 방식 :
             * -> 프로토타입 빈은 스프링 컨테이너에 의해 "주입 시 한 번" 만들어
             *      지는게 아닌 "get Bean"으로 조회할 때마다 새 인스턴스를 생성해서 반환
             * -> 의존관계를 외부에서 주입(DI) 받는게 아닌 직접 필요한 의존관계를 찾는 것을
             *      Dependency Lookup(DL) 의존관계 조회(=탐색)이라 함.
             * -> 이렇게 스프링의 애플리케이션 컨텍스틑 전체를 주입받게 되면, 스프링 컨테이너
             *      에 종속적인 코드가 되고, 단위 테스트도 어려워진다.
             * -> 현재 필요한 기능은 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 DL 정도의 기능만
             *      제공하는 무언가 있으면 된다.
             * ObjectFactory :
             * -> 기능이 단순, 별도의 라이브러리 필요 없음, 스프링에 의존
             * ObjectProvider :
             * -> 상속, 옵션, 스트림 처리등 편의 기능이 많고, 별도의 라이브러리 필요 없음, 스프링에 의존
             * Provider<> :
             * -> get() 메서드 하나로 기능이 매우 단순
             * -> 별도의 라이브러리 필요
             * -> 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용 가능
             */

//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = provider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
