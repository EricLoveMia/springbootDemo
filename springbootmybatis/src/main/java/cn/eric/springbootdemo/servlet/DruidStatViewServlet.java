package cn.eric.springbootdemo.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: DruidStatViewServlet
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 18:09
 **/
@WebServlet(urlPatterns = "/druid/*", initParams={
        @WebInitParam(name="allow",value=""),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name="deny",value=""),// IP黑名单 (存在共同时，deny优先于allow)
        @WebInitParam(name="loginUsername",value="admin"),// 用户名
        @WebInitParam(name="loginPassword",value="admin"),// 密码
        @WebInitParam(name="resetEnable",value="true")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {

}
