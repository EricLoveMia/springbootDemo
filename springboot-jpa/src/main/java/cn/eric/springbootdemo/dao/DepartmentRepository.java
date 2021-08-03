package cn.eric.springbootdemo.dao;

import cn.eric.springbootdemo.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: DepartmentRepository
 * @Description: TODO
 * @company lsj
 * @date 2019/5/21 16:23
 **/
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
