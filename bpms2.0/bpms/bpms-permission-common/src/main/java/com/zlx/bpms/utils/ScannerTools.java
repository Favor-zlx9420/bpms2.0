package com.zlx.bpms.utils;

import org.springframework.security.access.prepost.PreAuthorize;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Package: com.bpms.author.controller
 * @Author: LQW
 * @Date: 2020/3/17
 * @Description:包扫描工具
 */
public class ScannerTools {

    /**
     * 权限控制固定值
     */
    public static final String AUTH_PREFIX = "hasAuthority('')";
    public static final String ROLE_PREFIX = "hasRole('')";
    public static final String PERMISSION_PREFIX = "hasPermission('')";
    public static final String ANY_AUTH_PREFIX = "hasAnyAuthority('')";
    public static final String ANY_ROLE_PREFIX = "hasAnyRole('')";

    /**
     * 通过包名获取所有类
     *
     * @param pck 包名
     * @return
     */
    public static List<Class<?>> getAllClassByPackageName(Package pck) {
        String packageName = pck.getName();
        List<Class<?>> list = getClasses(packageName);
        return list;
    }

    /**
     * 通过接口名取得某个接口下所有实现这个接口的类
     */
    public static List<Class<?>> getAllClassByInterface(Class<?> c) {
        List<Class<?>> returnClassList = null;
        if (c.isInterface()) {
            // 获取当前的包名
            String packageName = c.getPackage().getName();
            // 获取当前包下以及子包下所以的类
            List<Class<?>> allClass = getClasses(packageName);
            if (allClass != null) {
                returnClassList = new ArrayList<Class<?>>();
                for (Class<?> cls : allClass) {
                    // 判断是否是同一个接口
                    if (c.isAssignableFrom(cls)) {
                        // 本身不加入进去
                        if (!c.equals(cls)) {
                            returnClassList.add(cls);
                        }
                    }
                }
            }
        }
        return returnClassList;
    }

    /**
     * 取得某一类所在包的所有类名 不含迭代
     */
    public static String[] getPackageAllClassName(String classLocation, String packageName) {
        // 将packageName分解
        String[] packagePathSplit = packageName.split("[.]");
        String realClassLocation = classLocation;
        int packageLength = packagePathSplit.length;
        for (int i = 0; i < packageLength; i++) {
            realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
        }
        File packeageDir = new File(realClassLocation);
        if (packeageDir.isDirectory()) {
            String[] allClassName = packeageDir.list();
            return allClassName;
        }
        return null;
    }

    /**
     * 获取类
     *
     * @param packageName
     * @return
     */
    private static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        boolean recursive = true;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    JarFile jar;
                    try {
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 通过文件查找并添加包中的类
     *
     * @param packageName
     * @param filePath
     * @param recursive
     * @param classes
     */
    private static void findAndAddClassesInPackageByFile(String packageName, String filePath, boolean recursive, List<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(filePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirFiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File pathname) {
                return (recursive && pathname.isDirectory()) || (pathname.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirFiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 注解是否有效
     *
     * @param list
     */
    public static Set<String> validAnnotation(List<Class<?>> list) {
        Set<String> set = new HashSet<>();
        if (null != list && list.size() > 0) {
            for (Class<?> clazz : list) {
                //获取类中的所有的方法
                Method[] methods = clazz.getDeclaredMethods();
                if (null != methods && methods.length > 0) {
                    for (Method method : methods) {
                        PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
                        if (null != annotation) {
                            set.addAll(replaceStr(annotation.value()));
                        }
                    }
                }
            }
        }
        return set;
    }

    private static List<String> replaceStr(String value) {
        List<String> list = new ArrayList<>();
        String prefix = "";
        if (value.contains(removeStr(AUTH_PREFIX))) {
            prefix = AUTH_PREFIX;
        } else if (value.contains(removeStr(ROLE_PREFIX))) {
            prefix = ROLE_PREFIX;
        } else if (value.contains(removeStr(PERMISSION_PREFIX))) {
            prefix = PERMISSION_PREFIX;
        } else if (value.contains(removeStr(ANY_AUTH_PREFIX))) {
            prefix = ANY_AUTH_PREFIX;
        } else if (value.contains(removeStr(ANY_ROLE_PREFIX))) {
            prefix = ANY_ROLE_PREFIX;
        }
        String suffix = prefix.substring(prefix.length() - 2);
        prefix = prefix.substring(0, prefix.length() - 2);
        value = value.replace(prefix, "").replace(suffix, "");
        String[] values = value.split(",");
        for (String str : values) {
            value = str.replace("'", "");
            list.add(value);
        }
        return list;
    }

    private static String removeStr(String value) {
        return value.replace("('')","");
    }
}
