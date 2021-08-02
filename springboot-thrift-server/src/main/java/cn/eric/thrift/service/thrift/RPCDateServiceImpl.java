package cn.eric.thrift.service.thrift;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RPCDateServiceImpl
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/7/28
 * @Version V1.0
 **/
@Service
public class RPCDateServiceImpl implements RPCDateService.Iface {
    /**
     * 模拟存储
     */
    private Map<Integer, Student> studentMap = new HashMap<>();

    @Override
    public String getDate(String userName) throws TException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("今天是yyyy年MM月dd日 E kk点mm分");
        String nowTime = simpleDateFormat.format(date);
        return "Hello " + userName + "\n" + nowTime;
    }

    @Override
    public boolean postStudent(Student student) throws TException {
        try {
            studentMap.put(student.getUserId(), student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Student getStudent(int userId) throws TException {
        return studentMap.get(userId);
    }
}

