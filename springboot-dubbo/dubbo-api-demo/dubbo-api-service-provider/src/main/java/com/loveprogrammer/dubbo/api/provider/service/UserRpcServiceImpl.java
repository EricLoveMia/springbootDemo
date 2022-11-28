package com.loveprogrammer.dubbo.api.provider.service;

import com.loveprogrammer.dubbo.api.rpc.api.UserRpcService;
import com.loveprogrammer.dubbo.api.rpc.api.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;

/**
 * Company: ClickPaaS
 *
 * @version 1.0.0
 * @description: 接口实现类
 * @author: 钱旭
 * @date: 2022-11-28 11:21
 **/
@Service(version = "${dubbo.provider.UserRpcService.version}")
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }
}
