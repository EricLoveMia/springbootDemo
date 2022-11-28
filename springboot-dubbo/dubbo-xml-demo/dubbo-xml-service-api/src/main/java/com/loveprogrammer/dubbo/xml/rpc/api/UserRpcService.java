package com.loveprogrammer.dubbo.xml.rpc.api;

import com.loveprogrammer.dubbo.xml.rpc.api.dto.UserDTO;

/**
 * @version 1.0.0
 * @description: 服务接口
 * @author: eric
 * @date: 2022-11-28 11:14
 **/
public interface UserRpcService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     */
    UserDTO get(Integer id);
}
