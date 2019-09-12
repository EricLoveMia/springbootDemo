package cn.eric.springbootdemo.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Department
 * @Description: TODO
 * @company lsj
 * @date 2019/5/21 16:17
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 部门名称
     */
    private String name;

}
