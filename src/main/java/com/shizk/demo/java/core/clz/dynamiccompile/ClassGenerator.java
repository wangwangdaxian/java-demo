package com.shizk.demo.java.core.clz.dynamiccompile;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Objects;

public class ClassGenerator {
    private final String classRootDir;

    public ClassGenerator() {
        this(".");
    }

    public ClassGenerator(String classRootDir) {
        this.classRootDir = classRootDir;
    }


    public Class<?> generate(String classFullName, String code, String jarFile) throws MalformedURLException,
                                                                                       ClassNotFoundException {
        JavaCompiler   compiler   = ToolProvider.getSystemJavaCompiler();
        JavaFileObject fileObject = new JavaSourceFromString(classFullName, code);

        File root = new File(classRootDir);
        if (!root.exists()) {
            root.mkdirs();
        }

        String jars = getJars(jarFile);
        Iterable<String> options = Arrays.asList(
                "-d", classRootDir, "-cp", jars + File.pathSeparator + classRootDir);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(fileObject);

        JavaCompiler.CompilationTask task = compiler.getTask(null, null, null, options, null, compilationUnits);

        // 动态编译
        boolean success = task.call();
        if (!success) {
            return null;
        }
        System.out.println("compile success root path = " + root.getPath());
        URL[] urls = new URL[]{root.toURI().toURL()};
        // 设置父类加载器
        URLClassLoader classLoader = new URLClassLoader(urls, ClassGenerator.class.getClassLoader());
        return Class.forName(classFullName, true, classLoader);
    }

    private String getJars(String jarFile) {
        File file = new File(jarFile);
        if (!file.exists()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (File jar : Objects.requireNonNull(file.listFiles())) {
            builder.append(jar.getPath()).append(File.pathSeparator);
        }

        return builder.toString();
    }
}
