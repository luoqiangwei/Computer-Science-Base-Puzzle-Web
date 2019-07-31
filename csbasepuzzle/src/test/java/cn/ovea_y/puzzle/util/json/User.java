package cn.ovea_y.puzzle.util.json;

import java.util.*;

public class User {
    private String name;
    private Car car;
    private HashMap id;
    private Object[] array;
    private Car[][] cars;
    private Set set;
    private List list;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

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
