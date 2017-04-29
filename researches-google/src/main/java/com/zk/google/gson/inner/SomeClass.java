package com.zk.google.gson.inner;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zk_chs on 4/26/17.
 */
public class SomeClass<T> {

    protected SomeClass (){

    }

    public static void main(String[] args) {
        SomeClass<List<String>> someClass = new SomeClass<List<String>>(){};
        
        Type superclass = someClass.getClass().getGenericSuperclass();
        ParameterizedType parameterized = (ParameterizedType) superclass;
        System.out.println(parameterized.getRawType());
        System.out.println(parameterized.getActualTypeArguments()[0]);

        ParameterizedType parameterized2 = (ParameterizedType) parameterized.getActualTypeArguments()[0];
        System.out.println(parameterized2.getRawType());
        System.out.println(parameterized2.getActualTypeArguments()[0]);
    }

}
