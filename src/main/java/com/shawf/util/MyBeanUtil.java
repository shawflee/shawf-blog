package com.shawf.util;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shawf_lee
 * @date 2020/6/11 16:03
 * Content:
 */
public class MyBeanUtil {
    public static String[] getNullPropertyNames(Object object) {
        BeanWrapper beanWrapper=new BeanWrapperImpl(object);
        PropertyDescriptor[] propertyDescriptors=beanWrapper.getPropertyDescriptors();
        List<String> nullPropertNames=new ArrayList<>();
        for(PropertyDescriptor pd:propertyDescriptors){
            String name=pd.getName();
            if(beanWrapper.getPropertyValue(name)==null){
                nullPropertNames.add(name);
            }
        }
        return nullPropertNames.toArray(new String[nullPropertNames.size()]);
    }
}
