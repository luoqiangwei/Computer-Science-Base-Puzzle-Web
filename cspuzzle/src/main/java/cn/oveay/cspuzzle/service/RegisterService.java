package cn.oveay.cspuzzle.service;

import cn.oveay.cspuzzle.bean.User;
import org.springframework.stereotype.Service;

public interface RegisterService {
    User register(User user);
    boolean check(User user);
}
