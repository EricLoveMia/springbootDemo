package com.loveprogrammer.springboot.elasticsearch.service.impl;

import com.loveprogrammer.springboot.elasticsearch.service.ListService;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UnixListService
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:03
 **/
public class UnixListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
