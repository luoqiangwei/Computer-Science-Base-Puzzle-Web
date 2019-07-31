package cn.ovea_y.puzzle.service.impl;

import cn.ovea_y.puzzle.bean.User;
import cn.ovea_y.puzzle.dao.UserDao;
import cn.ovea_y.puzzle.dao.impl.UserDaoImpl;
import cn.ovea_y.puzzle.service.RegisterService;
import cn.ovea_y.puzzle.util.commons.Nanoflake;
import cn.ovea_y.puzzle.util.security.SHA;

import java.util.Date;

public class RegisterServiceImpl implements RegisterService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User register(User user) {
        user.setPassword(SHA.SHA512Encoding(user.getPassword()));
        user.setNickname(user.getPhone());
        user.setAdmin(true);
        user.setEffective(true);
        user.setId(Nanoflake.getNanoflake());
        user.setAvatar("default.jpg");
        user.setCreateDate(new Date());
        userDao.insert(user);
        return user;
    }

    @Override
    public boolean check(User user) {
        return userDao.findByPhone(user.getPhone()) == null;
    }
}
