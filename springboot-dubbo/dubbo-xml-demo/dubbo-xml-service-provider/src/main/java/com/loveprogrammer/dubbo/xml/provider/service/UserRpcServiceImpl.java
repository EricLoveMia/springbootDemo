package com.loveprogrammer.dubbo.xml.provider.service;

import com.loveprogrammer.dubbo.xml.rpc.api.UserRpcService;
import com.loveprogrammer.dubbo.xml.rpc.api.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Company: ClickPaaS
 *
 * @version 1.0.0
 * @description: 接口实现类
 * @author: 钱旭
 * @date: 2022-11-28 11:21
 **/
@Service
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }
}
