package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @Target :
 * -> 해당 애노테이션을 어디에 적용할 수 있는 지정
 * @Retention :
 * -> 애노테이션 정보를 런타임까지 유지하겠다는 뜻
 * -> 즉 스프링 컨테이너가 리플렉션으로 해당 애노테이션을 읽을 수 있음
 * @Retention 종류 :
 *  SOURCE -> 컴파일할 때만 존재, class 파일에 안 남음
 *  CLASS -> class 파일에는 남지만 런타임에는 JVM이 안 읽음
 *  RUNTIME -> class 파일에도 남고, 런타임에 JVM / 스프링이 리플렉션으로 읽음
 * @Documented :
 * -> 해당 애노테이션을 javadoc 같은 문서에 표시할 수 있게 함
 * -> 필수는 아니지만 "관례"적으로 붙임
 * @Qualifiter("mainDiscountPolicy") :
 * -> 핵심.
 * -> @MainDiscountPolicy를 붙이면, 사실상 내부적으로 @Qualifier("mainDiscountPolicy")를 붙인 효과
 * -> 즉, @Qualifier("mainDiscountPolicy")을 반복해서 쓰지 않고, 깔끔하게 @MainDiscountPolicy만 쓰면 됨
 */
@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.TYPE,
        ElementType.ANNOTATION_TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
