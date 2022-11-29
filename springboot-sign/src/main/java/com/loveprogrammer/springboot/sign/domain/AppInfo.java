package com.loveprogrammer.springboot.sign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: AppInfo
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 13:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {

    private String appId;

    private String key;
}
