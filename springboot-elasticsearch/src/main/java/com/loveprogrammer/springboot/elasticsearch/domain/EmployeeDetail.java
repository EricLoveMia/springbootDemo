package com.loveprogrammer.springboot.elasticsearch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: EmployeeDetail
 * @Description: TODO
 * @company lsj
 * @date 2019/5/21 16:29
 **/
@Data
@Getter
@Setter
@ToString
public class EmployeeDetail {

    private Long employeeId;

    private String employeeName;

    private Long departmentId;

    private String departmentName;
}
