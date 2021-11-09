package cn.eric.serialization.service;

public interface ISerializer {

    /**
     * @MethodName: serialize
     * @Description: 序列化
     * @Param: [clasz]
     * @Return: byte[]
     * @Author: eric
     * @Date: 2021/8/27 18:58
     **/
    <T> byte[] serialize(T obj);

    /**
     * @MethodName: deserialize
     * @Description: 反序列化
     * @Param: [content, clazz]
     * @Return: T
     * @Author: eric
     * @Date: 2021/8/27 18:58
     **/
    <T> T deserialize(byte[] content, Class clazz);
}
