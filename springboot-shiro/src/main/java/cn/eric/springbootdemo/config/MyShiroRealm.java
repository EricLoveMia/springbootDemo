package cn.eric.springbootdemo.config;

import cn.eric.springbootdemo.domain.SysPermission;
import cn.eric.springbootdemo.domain.SysRole;
import cn.eric.springbootdemo.domain.UserInfo;
import cn.eric.springbootdemo.service.impl.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: MyShiroRealm
 * @Description: TODO
 * @company lsj
 * @date 2019/5/15 11:40
 **/
// @Configuration
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 授权：SimpleAuthorizationInfo用于存储用户的所有角色(Set<String> roles)和所有权限(Set<String> stringPermissions)信息
     * 当执行某个方法时，方法上会有权限注解，例如@RequiresPermissions("userInfo:add")，
     * 此时就会去找AuthorizationInfo中的stringPermissions是否包含userInfo:add，如果包含就继续处理，
     * 如果不包含则跳转到shiro配置的为授权的地址
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();

        for (SysRole role : userInfo.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission permision : role.getPermissions()) {
                authorizationInfo.addStringPermission(permision.getPermission());
            }
        }

        return authorizationInfo;
    }

    /**
     * 认证
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * 当用户登录时会执行
     *
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();

        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.findByUsername(userName);
        if (userInfo == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName() //realm name
        );
        return authenticationInfo;

    }
}
