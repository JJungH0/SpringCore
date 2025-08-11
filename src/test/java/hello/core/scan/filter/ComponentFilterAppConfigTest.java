package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        /**
         * org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'beanB' available
         * excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
         * 필터를 이용해서 제외를 해버렸으니 당연히 예외가 터져야지 정상이다.
         * -> type = FilterType.ANNOTATION (= 기본값이라 코드를 지워도 잘 동작한다.)
         */
        assertThrows(NoSuchBeanDefinitionException.class,
                        () -> ac.getBean("beanB", BeanB.class));


    }

    @Configuration
    @ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig {


    }
}
