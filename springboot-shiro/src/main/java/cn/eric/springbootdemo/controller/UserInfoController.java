package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.controller.dto.UserInfoDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserInfoController
 * @Description: TODO
 * @company lsj
 * @date 2019/5/15 17:31
 **/
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    /**
     * 用户查询
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")// 查看用户信息的权限
    public String userInfo() {
        return "userInfo";
    }

    /**
     * 用户添加
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")// 添加用户的权限
    public String userInfoAdd() {
        return "userInfoAdd";
    }

    /**
     * 用户删除
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")// 删除用户呢的权限
    public String userDel() {
        return "userInfoDel";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String userInfoList(@Valid @RequestBody UserInfoDto userInfoDto, BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldError = result.getFieldError();
            String field = fieldError.getField();
            String msg = fieldError.getDefaultMessage();
            return field + ":" + msg;
        }

        System.out.println(userInfoDto.toString());
        return "userInfo";
    }

}


