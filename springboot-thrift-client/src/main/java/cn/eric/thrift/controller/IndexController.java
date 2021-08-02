package cn.eric.thrift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName IndexController
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/2
 * @Version V1.0
 **/
@Controller
public class IndexController {
    /**
     * Swagger ui string.
     *
     * @return the string
     */
    @GetMapping("/")
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }
}
