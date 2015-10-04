package jdbc;

import java.text.MessageFormat;

public class Person {

    private Integer id;
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} (id: {1})", name, id);
    }

}
