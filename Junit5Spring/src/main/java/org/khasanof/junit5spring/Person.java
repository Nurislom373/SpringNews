package org.khasanof.junit5spring;

/**
 * Author: Nurislom
 * <br/>
 * Date: 4/27/2023
 * <br/>
 * Time: 9:59 AM
 * <br/>
 * Package: org.khasanof.junit5spring
 */
public class Person {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
