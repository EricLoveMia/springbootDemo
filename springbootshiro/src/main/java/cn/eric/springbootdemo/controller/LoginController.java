package cn.eric.springbootdemo.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: LoginController
 * @Description: TODO
 * @company lsj
 * @date 2019/5/15 17:22
 **/
@Controller
public class LoginController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "/index";
    }


    /**
     * 登录时先执行Realm中的认证方法，然后再执行登录方法
     *
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + exception;
                System.out.println("else -- >" + exception);
            }
        }

        map.put("msg", msg);

        // 此方法不处理登录成功,由shiro进行处理, 应为会在shiro中配置登录成功需要跳转的界面

        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "403";
    }

}
