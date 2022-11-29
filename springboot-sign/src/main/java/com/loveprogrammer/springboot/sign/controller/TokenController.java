package com.loveprogrammer.springboot.sign.controller;

import com.loveprogrammer.springboot.sign.util.MD5Util;
import com.loveprogrammer.springboot.sign.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: TokenController
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 11:10
 **/
@RestController
@RequestMapping("/api/token")
@Slf4j
public class TokenController {

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/api_token")
    public ApiResponse<AccessToken> apiToken(String appId, @RequestHeader("timestamp") String timestamp, @RequestHeader("sign") String sign) {
        Assert.isTrue(!StringUtils.isEmpty(appId) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(sign), "参数错误");
        long reqeustInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
        Assert.isTrue(reqeustInterval < 5 * 60 * 1000, "请求过期，请重新请求");

        // TODO 1 根据appId 查询数据库获取appSecret
        AppInfo appInfo = new AppInfo("1", "12345678954556");

        // 2.校验签名
        String signString = timestamp + appId + appInfo.getKey();
        String signature = MD5Util.encode(signString);
        log.info(signature);
        Assert.isTrue(signature.equals(sign), "签名错误");

        // 3 如果签名正确 生产一个token保存到redis中 如果错误返回错误信息
        AccessToken accessToken = this.saveToken(0, appInfo, null);

        return ApiResponse.success(accessToken);
    }

    @NotRepeatSubmit()
    @PostMapping("/user_token")
    public ApiResponse<UserInfo> userToken(String username, String password) {
        // 根据用户名查询密码 并比较密码(密码可以RSA加密一下）
        UserInfo userInfo = new UserInfo(username, "81255cb0dca1a5f304328a70ac85dcbd", "111111");
        String pwd = password + userInfo.getSalt();
        String passwordMD5 = MD5Util.encode(pwd);
        Assert.isTrue(passwordMD5.equals(userInfo.getPassword()), "密码错误");

        // 2 保存Token
        AppInfo appInfo = new AppInfo("1", "12345678954556");
        AccessToken accessToken = this.saveToken(1, appInfo, userInfo);
        userInfo.setAccessToken(accessToken);
        return ApiResponse.success(userInfo);
    }

    private AccessToken saveToken(int tokenType, AppInfo appinfo, UserInfo userInfo) {
        String token = UUID.randomUUID().toString();

        // token有效期为2小时
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.plusHours(2);

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date expireTime = Date.from(instant);
        // long expireTime = date.getTime();

        // 保存token
        ValueOperations<String, TokenInfo> operations = redisTemplate.opsForValue();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setTokenType(tokenType);
        tokenInfo.setAppInfo(appinfo);

        if (tokenType == 1) {
            tokenInfo.setUserInfo(userInfo);
        }

        operations.set(token, tokenInfo, 7200, TimeUnit.SECONDS);
        AccessToken accessToken = new AccessToken(token, expireTime);
        return accessToken;
    }

    public static void main(String[] args) {
//        long timestamp = System.currentTimeMillis();
//        System.out.println(timestamp);
//        String signString = timestamp + "1" + "12345678954556";


        String url = "productCode=TPIC_YWX_HKGTCKYWX&serviceType=2&channelCode=LINK5994941101826" + "&backUrl=http://c2b.zlbroker.com/c2b/html/ent/ent-standard/page/ent-standard.html?code=lmrlzl&from=singlemessage&isappinstalled=0";

        int index = url.indexOf("code=");

        url = url.substring(index);
        System.out.println(url);
        int index2 = url.indexOf("&");
        System.out.println(index2);

        String substring = url.substring(5, index2);
        // else url.substring(6);
        System.out.println(substring);
    }
}













































