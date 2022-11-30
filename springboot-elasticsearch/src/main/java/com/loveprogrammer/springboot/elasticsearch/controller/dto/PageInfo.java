package com.loveprogrammer.springboot.elasticsearch.controller.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: PageInfo
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:47
 **/
public class PageInfo {
    @ApiModelProperty(value = "页码", required = true)
    private int page;

    @ApiModelProperty(value = "每页条数", required = true)
    private int size;

    public PageInfo() {
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
