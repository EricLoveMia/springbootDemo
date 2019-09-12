package cn.eric.springbootdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: HuaWeiPhoneBean
 * @Description: TODO
 * @company lsj
 * @date 2019/7/2 11:21
 **/
@ToString
@Getter
@Setter
public class HuaWeiPhoneBean {

    private String productName;

    private List<ColorModeBean> colorsItemMode;

    private List<String> sellingPoints;


}
