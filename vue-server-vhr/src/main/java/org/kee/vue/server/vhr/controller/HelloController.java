package org.kee.vue.server.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 羽子少年
 * @Date： 2020/6/10 23:57
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "HELLO 恭喜您！您已成功访问受保护资源";
    }


}
