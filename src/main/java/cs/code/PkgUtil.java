package cs.code;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@Slf4j
public class PkgUtil {
    static List<DdbModel> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Set<Class<?>> set = getClzFromPkg("com/huami/device/common/service/user/entities");
        set.forEach(x -> {
            System.out.println(x.getName());
//            execute(x);
        });
        File file = new File(System.getProperty("user.dir") + "/ddb.txt");
//        String res = JsonUtils.serialize(list);
//        FileUtils.writeStringToFile(file, res, "UTF-8");
    }
/*
    private static void execute(Class<?> x) {
        Field[] fields = x.getDeclaredFields();
        Method[] methods = x.getDeclaredMethods();
        if (!x.isAnnotationPresent(DynamoDBTable.class)) {
            return;
        }
        Map<String, String> map = Stream.of(fields).filter(q -> q.isAnnotationPresent(DynamoDBAttribute.class) || q.isAnnotationPresent(DynamoDBHashKey.class) || q.isAnnotationPresent(DynamoDBRangeKey.class)).collect(Collectors.toMap(yy -> {
            DynamoDBHashKey name2 = yy.getAnnotation(DynamoDBHashKey.class);
            DynamoDBRangeKey name3 = yy.getAnnotation(DynamoDBRangeKey.class);
            DynamoDBAttribute name = yy.getAnnotation(DynamoDBAttribute.class);

            if (name2 != null && StringUtils.isNotEmpty(name2.attributeName())) {
                return name2.attributeName();
            }

            if (name3 != null && StringUtils.isNotEmpty(name3.attributeName())) {
                return name3.attributeName();
            }

            if (name != null && StringUtils.isNotEmpty(name.attributeName())) {
                return name.attributeName();
            }

            return yy.getName();
        }, xx -> xx.getType().getSimpleName()));

        Map<String, String> map1 = Stream.of(methods).filter(xx -> xx.isAnnotationPresent(DynamoDBRangeKey.class) || xx.isAnnotationPresent(DynamoDBAttribute.class) || xx.isAnnotationPresent(DynamoDBIndexRangeKey.class))
                .collect(Collectors.toMap(xx -> xx.getReturnType().getSimpleName(),
                        yy -> {
                    DynamoDBRangeKey name2 = yy.getAnnotation(DynamoDBRangeKey.class);
                    if (name2 != null && StringUtils.isNotEmpty(name2.attributeName())) {
                        return name2.attributeName();
                    }
                    DynamoDBAttribute name3 = yy.getAnnotation(DynamoDBAttribute.class);
                    if (name3 != null && StringUtils.isNotEmpty(name3.attributeName())) {
                        return name3.attributeName();
                    }
                    DynamoDBIndexRangeKey name = yy.getAnnotation(DynamoDBIndexRangeKey.class);
                    if (name != null && StringUtils.isNotEmpty(name.attributeName())) {
                        return name.attributeName();
                    }

                    String ss = yy.getName().substring(3);
                    char[] charArray = ss.toCharArray();
                    charArray[0] -= 32;
                    return String.valueOf(charArray);
                }));
        map.putAll(map1);
        DdbModel ddbModel = new DdbModel();
        ddbModel.setMap(map);
        DynamoDBTable annotation = x.getAnnotation(DynamoDBTable.class);
        if (StringUtils.isNotEmpty(annotation.tableName())) {
            ddbModel.setTableName(annotation.tableName());
        } else {
            ddbModel.setTableName(x.getSimpleName());
        }
        list.add(ddbModel);
    }
*/
    /**
     * 扫描包路径下所有的class文件
     *
     * @param pkg
     * @return
     */
    public static Set<Class<?>> getClzFromPkg(String pkg) {
        Set<Class<?>> classes = new LinkedHashSet<>();
        String userdir = System.getProperty("user.dir");
        System.out.println(userdir);
        System.out.println(PkgUtil.class.getResource(""));
        System.out.println(Object.class.getResource("/"));
        String pkgDirName = pkg.replace('.', '/');
        try {
            Enumeration<URL> urls = PkgUtil.class.getClassLoader().getResources(pkgDirName);
            System.out.println(PkgUtil.class.getClassLoader().getResources(pkgDirName).toString());
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) { // 如果是以文件的形式保存在服务器上
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");// 获取包的物理路径
                    findClassesByFile(pkg, filePath, classes);
                } else if ("jar".equals(protocol)) {  // 如果是jar包文件
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    findClassesByJar(pkg, jar, classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


    /**
     * 扫描包路径下的所有class文件
     *
     * @param pkgName 包名
     * @param pkgPath 包对应的绝对地址
     * @param classes 保存包路径下class的集合
     */
    private static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) {
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }


        // 过滤获取目录，or class文件
        File[] dirfiles = dir.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));


        if (dirfiles == null || dirfiles.length == 0) {
            return;
        }


        String className;
        Class clz;
        for (File f : dirfiles) {
            if (f.isDirectory()) {
                findClassesByFile(pkgName + "." + f.getName(),
                        pkgPath + "/" + f.getName(),
                        classes);
                continue;
            }


            // 获取类名，干掉 ".class" 后缀
            className = f.getName();
            className = className.substring(0, className.length() - 6);

            // 加载类
            if (className.contains("$")) {
                continue;
            }

            clz = loadClass(pkgName.replace("/", ".") + "." + className);
            if (clz != null) {
                classes.add(clz);
            }
        }
    }


    /**
     * 扫描包路径下的所有class文件
     *
     * @param pkgName 包名
     * @param jar     jar文件
     * @param classes 保存包路径下class的集合
     */
    private static void findClassesByJar(String pkgName, JarFile jar, Set<Class<?>> classes) {
        String pkgDir = pkgName.replace(".", "/");


        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        String name;
        String className;
        Class<?> claze;
        while (entry.hasMoreElements()) {
            jarEntry = entry.nextElement();

            name = jarEntry.getName();
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }


            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                // 非指定包路径， 非class文件
                continue;
            }


            // 去掉后面的".class", 将路径转为package格式
            className = name.substring(0, name.length() - 6);
            claze = loadClass(className.replace("/", "."));
            if (claze != null) {
                classes.add(claze);
            }
        }
    }


    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            log.error("load class error! clz: {}, e:{}", fullClzName, e);
        }
        return null;
    }

    static class DdbModel {
        String tableName;
        Map<String, String> map;

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

}
