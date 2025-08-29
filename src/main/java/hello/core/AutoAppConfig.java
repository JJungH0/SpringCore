package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * ComponentScan:
 * -> @Component, @Service, @Repository, @Controller로 등록된 클래스들을 자동으로
 * -> 스프링 컨테이너에 Bean으로 등록하는 기능
 * -> 즉 일일이 각 메서드에 @Bean을 붙일 필요없다.
 * -> 빈 이름 기본 전략 (= MemberServiceImpl -> memberServiceImpl)
 * -> 빈 이름 직접 지정 (= @Component("memberService"))
 *
 */
@Configuration
@ComponentScan(
        // 만약 Packages를 지정하지 않는다면?
        // default값으로 -> 같다. (basePackageClasses = AutoAppConfig.class, // 해당 클래스의 패키지부터 스캔)
        // -> hello.core라는 패키지를 포함한 하위 패키지내에서 @Component가 붙은 클래스를 찾아서 등록
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class, // 해당 클래스의 패키지부터 스캔
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {

    /**
     * Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
     */
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
