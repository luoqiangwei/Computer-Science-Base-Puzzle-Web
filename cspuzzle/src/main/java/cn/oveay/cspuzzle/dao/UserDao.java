package cn.oveay.cspuzzle.dao;

import cn.ovea_y.puzzle.bean.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    void insert(User user);
    void update(User user);
    void delete(String id);
    List<User> findAll();
    List<User> findAllEff();
    User findById(String id);
    User findByPhone(String phone);
    void updateState(String id, boolean state);
}
