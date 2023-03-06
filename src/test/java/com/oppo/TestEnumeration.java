package com.oppo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 测试枚举类
 */
public class TestEnumeration {
    @Test
    public void display1(){
        ArrayList<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("CC");

        // List-->Enumeration
        Enumeration<String> stringEnumeration = Collections.enumeration(list);
        while (stringEnumeration.hasMoreElements()){
            System.out.println(stringEnumeration.nextElement());
        }
    }

    @Test
    public void display2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("CC");

        Enumeration<String> stringEnumeration = Collections.enumeration(list);
        // Enumeration-->List
        List<String> tempList = Collections.list(stringEnumeration);
        System.out.println(tempList);
    }
}
