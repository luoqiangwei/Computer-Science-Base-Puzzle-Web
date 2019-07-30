package cn.ovea_y.puzzle.util.json;

import java.util.Map;

public class User {
    private String name;
    private Car car;
    private Map id;

    public Map getId() {
        return id;
    }

    public void setId(Map id) {
        this.id = id;
    }

    public User(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
