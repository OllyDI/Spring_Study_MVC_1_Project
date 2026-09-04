package study.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Controller는 반환 값이 String이면 뷰 이름으로 인식
 *  RestController는 반환 값으로 뷰를 찾지 않고 HTTP 메시지 바디에 바로 입력
**/

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log{}", name);
        log.debug("debug log{}", name);
        log.info("info log={}", name);
        log.warn("warn log{}", name);
        log.error("error log{}", name);

        return "ok";
    }
}
