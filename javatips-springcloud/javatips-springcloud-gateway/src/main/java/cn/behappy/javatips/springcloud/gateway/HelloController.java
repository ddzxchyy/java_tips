package cn.behappy.javatips.springcloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", 0);
        map.put("msg", "hello world");
        return map;
    }

    @GetMapping("/")
    public Map<String, Object> helloWorld() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("code", 0);
        map.put("msg", "hello world");
        return map;
    }
}
