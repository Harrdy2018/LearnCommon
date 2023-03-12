package com.learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
class Employee {
    private Integer id;

    private String name;

    private Integer salary;
}

/**
 *  FlatMap方法使用:扁平化操作,将多维嵌套列表转换为单维列表
 *
 */
public class Main20230312 {
    private static final List<Employee> list = Arrays.asList(
            new Employee(1, "Alex", 1000),
            new Employee(2, "Michael", 2000),
            new Employee(3, "Jack", 1500),
            new Employee(4, "Owen", 1500),
            new Employee(5, "Denny", 2000));

    private static final List<List<Employee>> listFlat = Arrays.asList(
            Arrays.asList(
                    new Employee(1, "Alex", 1000),
                    new Employee(2, "Michael", 2000)),
            Arrays.asList(
                    new Employee(3, "Jack", 1500),
                    new Employee(4, "Owen", 1500)),
            Arrays.asList(
                    new Employee(5, "Denny", 2000),
                    new Employee(6, "Huawei", 7000)));

    @Test
    public void display1(){
        String words[] = new String[]{"Hello", "World World", "Hello"};

        List<String> aa = Arrays.stream(words).map(temp -> temp.toUpperCase(Locale.ROOT)).distinct().collect(Collectors.toList());
        System.out.println(aa);

        List<String []> bb=Arrays.stream(words).map(temp->temp.split(" ")).collect(Collectors.toList());
        System.out.println(bb);

        List<String []> cc=Arrays.stream(words).map(temp->temp.split("")).collect(Collectors.toList());

        Arrays.stream(words).map(temp->temp.split("")).flatMap(temp->Arrays.stream(temp)).distinct().collect(Collectors.toList());
    }

    /**
     * peek 主要用于支持调试
     * display2执行步骤 filter,peek和map都属于中间操作
     */
    @Test
    public void display2(){
        Stream.of("aa", "bbb", "cccc", "ddddd")
                .filter(e -> {
                    System.out.println("In filter e:" + e);
                    return e.length() > 3;
                })
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    @Test
    public void display3(){
        List<Employee> employeesAll = listFlat.stream().flatMap(Collection::stream).collect(Collectors.toList());
        Assert.assertTrue(employeesAll.size() == 6);
    }

    @Test
    public void display4(){
        List<Employee> employeesAll = listFlat.stream().flatMap(Collection::stream).collect(Collectors.toList());
        List<Employee> employeesAll2 = listFlat.stream().flatMap(employees -> {
            Stream<Employee> stream = employees.stream();
            return stream;
        }).collect(Collectors.toList());
        Assert.assertEquals(employeesAll, employeesAll2);
    }

    @Test
    public void display5(){
        List<Long> listFlatLong = listFlat.stream()
                .flatMap(employees -> employees.stream())
                .peek(System.out::println)
                .flatMapToLong(employee -> LongStream.of(employee.getId()))
                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());
    }

    @Test
    public void display6(){
        List<Long> listFlatLong2 = listFlat.stream()
                .flatMap(employees -> employees.stream())
                .peek(System.out::println)
                .mapToLong(Employee::getId)
                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());
    }

    @Test
    public void display7(){
        List<Integer> listFlatName = listFlat.stream()
                .flatMap(employees -> employees.stream())
                .peek(System.out::println)
                .map(employee -> employee.getSalary())
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    /**
     * boxed的作用就是将int类型的stream转成了Integer类型的Stream
     */
    @Test
    public void display8(){
        Random random = new Random();
        IntStream intStream = random.ints(0, 100);
        List<Integer> integerList = intStream
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(integerList);
    }
}
