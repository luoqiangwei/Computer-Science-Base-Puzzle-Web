package cn.ovea_y.puzzle.util.json;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private Car car;
    private HashMap id;
    private Object[] array;
    private Car[][] cars;

    public Car[][] getCars() {
        return cars;
    }

    public void setCars(Car[][] cars) {
        this.cars = cars;
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public HashMap getId() {
        return id;
    }

    public void setId(HashMap id) {
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
