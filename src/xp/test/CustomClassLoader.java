package xp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomClassLoader  extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                throw new FileNotFoundException(name);
            } else {
                // defineClass方法将字节码转化为类
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) throws ClassNotFoundException {
        // 从自定义路径中加载指定类，返回类的字节码文件
        String fileName = name.substring(name.lastIndexOf('.') + 1);
        URL url = getClass().getResource(fileName + ".class");
        try {
            byte[] b = Files.readAllBytes(Path.of(url.toURI()));
            return b;
        } catch (IOException | URISyntaxException e) {
            throw new ClassNotFoundException(name);
        }

    }

    private static Object getNewClass(String name) throws Exception {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> cl =customClassLoader.findClass(name);
        return cl.getDeclaredConstructor().newInstance();
    }

    public static void main(String[] args) throws Exception {
        SupperTest test1 = (SupperTest) getNewClass("xp.test.xp.test");
        SupperTest test2 = (SupperTest) getNewClass("xp.test.xp.test");

        System.out.println(test1.getClass());
        System.out.println(test2.getClass());
        test1.commonMethod();
        test2.commonMethod();

    }
}

