package com.shizk.demo.java.core.clz.map;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    /**
     * Map转换成JavaBean.
     *
     * @param type JavaBean的Class
     * @param map  map
     * @return 转换的JavaBean
     * @throws IntrospectionException    无法获取类字段
     * @throws IllegalAccessException    JavaBean无法被实例化
     * @throws InstantiationException    JavaBean无法被实例化
     * @throws InvocationTargetException 调用方法失败
     */
    public static Object toBean(Class<?> type, Map<String, ?> map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //获取JavaBean其所有属性、公开的方法和事件
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        //实例化JavaBean对象
        Object obj = type.newInstance();
        //获取属性数组
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            //取出一个属性
            //获取属性名称
            String propertyName = descriptor.getName();
            //判断属性是否存在于map
            if (map.containsKey(propertyName)) {
                //获取属性对应值
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                //把值设置到当前属性
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    /**
     * JavaBean转换成Map
     *
     * @param bean JavaBean类
     * @return map对象
     * @throws IntrospectionException    无法获取类字段
     * @throws IllegalAccessException    JavaBean无法被实例化
     * @throws InvocationTargetException 调用方法失败
     */
    public static Map<String, Object> toMap(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        //定义Map对象
        Map<String, Object> returnMap = new HashMap<String, Object>();
        //获取JavaBean其所有属性、公开的方法和事件
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        //获取属性数组
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            //取出一个属性
            PropertyDescriptor descriptor = propertyDescriptors[i];
            //获取属性名称
            String propertyName = descriptor.getName();
            //过滤class
            if (!propertyName.equals("class")) {
                //获取属性方法，用于读取属性值的方法
                Method readMethod = descriptor.getReadMethod();
                //调用方法返回当前属性值
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    //将属性名称和属性对应值放入map
                    returnMap.put(propertyName, result);
                } else {
                    //如果无值，默认为空字符串
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }


    public static void main(String[] args) throws Exception {

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", 1);
        objectMap.put("name", "张三");

        //Map转JavaBean
        UserBean userBean = (UserBean) toBean(UserBean.class, objectMap);
        System.out.println(userBean.getId());
        System.out.println(userBean.getName());
        //1
        //张三

        //JavaBean转Map
        System.out.println(toMap(userBean));
        //{id=1, name=张三}

    }
}
