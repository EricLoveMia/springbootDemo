package com.loveprogrammer.springboot.jpa.dao;

import com.loveprogrammer.springboot.jpa.domain.Employee;
import com.loveprogrammer.springboot.jpa.domain.EmployeeDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: EmployeeRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/5/21 16:22
 **/
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * 根据部门ID 获取员工数量
     *
     * @return int
     * @throws
     * @author Eric
     * @date 16:25 2019/5/21
     * @params departmentId
     **/
    int countByDepartmentId(Long departmentId);

    /**
     * 根据部门Id 分页查询
     *
     * @return org.springframework.data.domain.Page<cn.eric.springbootdemo.domain.Employee>
     * @throws
     * @author Eric
     * @date 16:26 2019/5/21
     * @params departmentId
     * @params pageable
     **/
    Page<Employee> queryByDepartmentId(Long departmentId, Pageable pageable);

    /**
     * 根据员工id升序查询前10条记录
     *
     * @return java.util.List<cn.eric.springbootdemo.domain.Employee>
     * @throws
     * @author Eric
     * @date 16:27 2019/5/21
     **/
    List<Employee> readTop10ByOrderById();

    /**
     * 根据员工姓名取第一条记录
     *
     * @return cn.eric.springbootdemo.domain.Employee
     * @throws
     * @author Eric
     * @date 16:28 2019/5/21
     * @params name
     * @params sort
     **/
    Employee getFirstByName(String name, Sort sort);

    @Query("select e.id as employeeId,e.name as employeeName,d.id as departmentId,d.name as department from Employee e,Department d where e.id = ?1 and d.id = ?2")
    EmployeeDetail getEmployeeJoinDepartment(Long eid, Long did);

    @Modifying
    @Transactional(timeout = 10)
    @Query("update Employee e set e.name = ?1 where e.id = ?2")
    int modifyEmployeeNameById(String name, Long id);

}
