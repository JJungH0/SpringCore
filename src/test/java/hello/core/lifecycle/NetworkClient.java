package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class NetworkClient /*implements InitializingBean, DisposableBean */{
    private String url;


    public NetworkClient() {
        log.debug("생성자 호출 url :{}",url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        log.debug("connect : {}",url);
    }

    public void call(String message) {
        log.debug("call : {}, message = {}", url, message);
    }

    public void disconnect() {
        log.debug("close : {}",url);
    }

    @PostConstruct
    public void init() {
        log.debug("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        log.debug("NetworkClient.close");
        disconnect();
    }

    /**
     * InitializingBean :
     * -> afterPropertiesSet() 메서드로 초기화를 지원
     */

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }

    /**
     * DisposableBean :
     * -> destroy() 메서드로 소멸을 지원
     */

//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
}
