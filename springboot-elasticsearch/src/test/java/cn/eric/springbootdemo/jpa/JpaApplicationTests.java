package cn.eric.springbootdemo.jpa;

import com.loveprogrammer.springboot.elasticsearch.dao.DepartmentRepository;
import com.loveprogrammer.springboot.elasticsearch.dao.EmployeeRepository;
import com.loveprogrammer.springboot.elasticsearch.dao.UserRepository;
import com.loveprogrammer.springboot.elasticsearch.domain.Department;
import com.loveprogrammer.springboot.elasticsearch.domain.Employee;
import com.loveprogrammer.springboot.elasticsearch.domain.EmployeeDetail;
import com.loveprogrammer.springboot.elasticsearch.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: JpaApplicationTests
 * @Description: TODO
 * @company lsj
 * @date 2019/5/10 15:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void testJPA() {
        userRepository.deleteAll();

        User user = new User("eric", 32, new Date());
        userRepository.save(user);

        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 10));

        System.out.println("getTotalElements" + userPage.getTotalElements());
        //User user1 = userRepository.findUser("mengday", 20);
        User user2 = userRepository.findUser2("eric");

        int result = userRepository.updateAge(18);

        System.out.println(user2);
    }

    @Test
    public void initData() {

        employeeRepository.deleteAll();
        departmentRepository.deleteAll();

        Department department1 = new Department();
        department1.setName("开发部");
        departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setName("测试部");
        departmentRepository.save(department2);

        String[] names = new String[]{"lucy", "tom", "hanmeime", "jacky", "francky", "lilly", "xiaoming", "smith", "walt", "sherry"};

        // 员工表中增加10条记录
        for (int i = 0; i < 10; i++) {
            Employee emp = new Employee();
            emp.setName(names[i]);
            if (i < 5) {
                emp.setDepartment(department1);
            } else {
                emp.setDepartment(department2);
            }
            employeeRepository.save(emp);
        }

    }

    @Test
    public void testCountByDepartmentId() {
        int count = employeeRepository.countByDepartmentId(1L);
        System.out.println(count);
    }

    @Test
    public void testQueryByDepartmentId() {
        Pageable pageable = PageRequest.of(0, 3, new Sort(Sort.Direction.ASC, "name"));
        Page<Employee> employees = employeeRepository.queryByDepartmentId(1L, pageable);

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }

    @Test
    public void testReadTop10ByOrderOyId() {
        List<Employee> emps = employeeRepository.readTop10ByOrderById();
        for (Employee emp : emps) {
            System.out.println("员工姓名：" + emp.getName() + "，所属部门：" + emp.getDepartment().getName());
        }
    }

    @Test
    public void testGetFirstByName() {
        Employee emp = employeeRepository.getFirstByName("xiaoming", new Sort(Sort.Direction.ASC, "id"));
        System.out.println("员工姓名：" + emp.getName() + "，所属部门：" + emp.getDepartment().getName());
    }

    @Test
    public void testGetEmployeeJoinDepartment() {
        EmployeeDetail empDetail = employeeRepository.getEmployeeJoinDepartment(5L, 19L);
        System.out.println("员工姓名：" + empDetail.getEmployeeName() + "，部门名称：" + empDetail.getDepartmentName());
    }

    @Test
    public void testModifyEmployeeNameById() {
        employeeRepository.modifyEmployeeNameById("chris", 5L);
    }

    @Test
    public void testDeleteById() {
        employeeRepository.deleteById(11L);
    }

    @Test
    public void testOther() {
        System.out.println(Integer.parseInt("1.0"));
    }

}
