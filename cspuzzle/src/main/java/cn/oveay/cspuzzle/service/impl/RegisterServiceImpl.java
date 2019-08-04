package cn.oveay.cspuzzle.service.impl;

import cn.oveay.cspuzzle.bean.User;
import cn.oveay.cspuzzle.dao.UserDao;
import cn.oveay.cspuzzle.service.RegisterService;
import cn.oveay.cspuzzle.util.commons.Nanoflake;
import cn.oveay.cspuzzle.util.security.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserDao userDao;

    @Override
    public User register(User user) {
        user.setPassword(SHA.SHA512Encoding(user.getPassword()));
        user.setNickname(user.getPhone());
        user.setIsAdmin(true);
        user.setIsEffective(true);
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
