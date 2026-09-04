package study.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import study.springmvc.basic.HelloData;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("request-body-json-v1")
    public void RequestBodyJsonV1(HttpServletRequest req, HttpServletResponse res) throws IOException {

        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData);

        res.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("request-body-json-v2")
    public String RequestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}", helloData);

        return "ok";
    }
    
    
    // @RequestBody를 생략하면 값이 안들어감 -> @ModelAttribute가 되어버림
    // String, int, Integer -> @RequestParam
    // 그 외 나머지 -> @ModelAttribute
    @ResponseBody
    @PostMapping("request-body-json-v3")
    public String RequestBodyJsonV3(@RequestBody HelloData data) {
        log.info("helloData={}", data);
        return "ok";
    }

    @ResponseBody
    @PostMapping("request-body-json-v4")
    public String RequestBodyJsonV4(HttpEntity<HelloData> httpEntity) {

        HelloData data = httpEntity.getBody();
        log.info("helloData={}", data);
        return "ok";
    }

    @ResponseBody
    @PostMapping("request-body-json-v5")
    public HelloData RequestBodyJsonV5(@RequestBody HelloData data) {
        log.info("helloData={}", data);
        return data;
    }
}
