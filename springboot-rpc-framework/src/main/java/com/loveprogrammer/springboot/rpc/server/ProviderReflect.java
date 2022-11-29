package com.loveprogrammer.springboot.rpc.server;

import org.apache.commons.beanutils.MethodUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ProviderReflect
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/8/13
 * @Version V1.0
 **/
public class ProviderReflect {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();


    public static void provider(final Object service, int port) throws Exception {
        if (service == null || port <= 0 || port > 65535) {
            throw new IllegalArgumentException("Illegal param.");
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                try {
                    try {
                        // 获取输入流
                        InputStream inputStream = socket.getInputStream();
                        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

                        // method name
                        String methodName = objectInputStream.readUTF();
                        //method arguments
                        Object[] arguments = (Object[]) objectInputStream.readObject();

                        //
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                        try {
                            Object result = MethodUtils.invokeExactMethod(service, methodName, arguments);
                            objectOutputStream.writeObject(result);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IOException e) {
                            objectOutputStream.writeObject(e);
                        } finally {
                            objectOutputStream.close();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }


    }
}
