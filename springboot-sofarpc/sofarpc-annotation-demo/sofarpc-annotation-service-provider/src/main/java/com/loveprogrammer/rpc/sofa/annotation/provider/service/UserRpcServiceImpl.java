package com.loveprogrammer.rpc.sofa.annotation.provider.service;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.loveprogrammer.rpc.sofa.annotation.api.UserRpcService;
import com.loveprogrammer.rpc.sofa.annotation.api.dto.UserAddDTO;
import com.loveprogrammer.rpc.sofa.annotation.api.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
@SofaService(bindings = @SofaServiceBinding(bindingType = "bolt"))
public class UserRpcServiceImpl implements UserRpcService {

    @Override
    public UserDTO get(Integer id) {
        return new UserDTO().setId(id)
                .setName("没有昵称：" + id)
                .setGender(id % 2 + 1); // 1 - 男；2 - 女
    }

    @Override
    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }

}
