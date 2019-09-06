package cn.eric.springbootdemo.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: Employee
 * @Description: TODO
 * @company lsj
 * @date 2019/5/21 16:12
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
