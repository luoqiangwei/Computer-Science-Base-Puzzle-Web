package cn.ovea_y.puzzle.dao.impl;

import cn.ovea_y.puzzle.bean.User;
import cn.ovea_y.puzzle.dao.UserDao;
import cn.ovea_y.puzzle.util.jdbc.TransactionQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner = new TransactionQueryRunner();

    @Override
    public void insert(User user) {
        String sql = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            queryRunner.update(sql, new Object[]{user.getId(), user.getNickname(), user.getPhone(), user.getPassword(), user.getGender(), user.getAvatar(), user.getCreateDate(), user.getCreateDate(), user.getCreateDate(), user.getRealName(), user.getIdCard(), user.getAdmin(), user.getEffective()});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        String sql = "update user set nickname=?,password=?,gender=?,avatar=?,realName=?,idCard=? where id=?";
        try {
            queryRunner.update(sql, user.getNickname(), user.getPassword(), user.getGender(), user.getAvatar(), user.getRealName(), user.getIdCard(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "update user set isEffective=0 where id=?";
        try {
            queryRunner.update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        try {
            return queryRunner.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllEff() {
        String sql = "select * from user where isEffective=1";
        try {
            return queryRunner.query(sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(String id) {
        String sql = "select * from user where id=?";
        try {
            return queryRunner.query(sql, new BeanHandler<User>(User.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByPhone(String phone) {
        String sql = "select * from user where phone=?";
        try {
            return queryRunner.query(sql, new BeanHandler<User>(User.class), phone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(String id, boolean state) {
        String sql = "update user set isEffective=? where id=?";
        try {
            queryRunner.update(sql, state, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
