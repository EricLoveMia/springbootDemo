package cn.eric.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ApiResult
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 11:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {
    /** 代码 */
    private String code;
    /** 结果 */
    private String msg;
}
