package jdbc;

import java.text.MessageFormat;

public class Person {

    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} ({1})", name, age);
    }

}
