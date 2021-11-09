package cn.eric.serialization.service.impl;

import cn.eric.serialization.model.User;
import cn.eric.serialization.service.ISerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DefaultJavaSerializer
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/27
 * @Version V1.0
 **/
public class DefaultJavaSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] content, Class clazz) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        User u = new User();
        u.setEmail("liyebing@163.com");
        u.setName("kongxuan");

        User u1 = new User();
        u1.setEmail("liyebing@162.com");
        u1.setName("kongxuan11");

        List<User> userList = new ArrayList<User>();
        Map<String, User> userMap = new HashMap<String, User>();
        userList.add(u1);
        userMap.put("a", u1);

        u.setUserList(userList);
        u.setUserMap(userMap);


        byte[] userByte = new DefaultJavaSerializer().serialize(u);
        User user = new DefaultJavaSerializer().deserialize(userByte, User.class);
        System.out.println(user.getEmail() + " : " + user.getName() + " : " + new String(new DefaultJavaSerializer().serialize(u.getUserList())) + " : " + new String(new DefaultJavaSerializer().serialize(u.getUserMap())));
    }
}
