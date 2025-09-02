package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        /**
         * LifeCycleConfig 클래스 정보를 읽어서 컨테이너 생성
         */
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        /**
         * ac.getBean("빈 이름", "반환 타입");
         * -> 빈 이름 (= networkClient)
         * -> 반환 타입 (= NetworkClient.class)
         */
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{

        // 설정 정보에 초기화 소멸 메서드 지정
        @Bean/*(initMethod = "init", destroyMethod = "close")*/
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
