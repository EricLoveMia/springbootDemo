package com.loveprogrammer.springboot.mybatis.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: DruidStatFilter
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 18:06
 **/
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {
}
