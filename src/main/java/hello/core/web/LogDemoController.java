package hello.core.web;

import hello.core.common.MyLogger;
import hello.core.logdemo.LogDemoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService service;
//    private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    private final MyLogger logger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String reqeustURL = request.getRequestURI().toString();
//        MyLogger logger = myLoggerObjectProvider.getObject();
        logger.setRequestURL(reqeustURL);

        logger.log("controller test");
        service.logic("testId");
        System.out.println("logger = " + logger.getClass());

        return "OK";
    }
}
