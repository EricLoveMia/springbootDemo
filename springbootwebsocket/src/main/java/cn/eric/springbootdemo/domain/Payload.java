package cn.eric.springbootdemo.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Payload
 * @Description: TODO
 * @company lsj
 * @date 2019/9/12 16:54
 **/
@Data
@ToString
@RequiredArgsConstructor
public class Payload {

    private String from;
    private String to;
    private String content;
}
