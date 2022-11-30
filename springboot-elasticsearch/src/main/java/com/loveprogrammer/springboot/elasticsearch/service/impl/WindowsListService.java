package com.loveprogrammer.springboot.elasticsearch.service.impl;

import com.loveprogrammer.springboot.elasticsearch.service.ListService;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: WindowsListService
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:03
 **/
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
