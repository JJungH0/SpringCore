package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.bean.override.convention.TestBean;

import java.util.Optional;

@Slf4j
public class AutowiredTest {

    @Test
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        /**
         * 의존관계가 없다면 해당 메서드는 호출 자체가 안된다.
         * Member는 스프링 빈이 아니다.
         *
         */
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            log.debug("noBean1 = {}",noBean1);
        }

        /**
         * 자동 주입 대상이 없다면 null을 반환한다.
         * @param null
         */
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            log.debug("noBean2 = {}",noBean2);
        }

        /**
         * 자동 주입 대상이 없다면 Optional.empty를 반환한다.
         * @param Optional.empty
         */
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            log.debug("noBean3 = {}",noBean3);
        }
    }

}
