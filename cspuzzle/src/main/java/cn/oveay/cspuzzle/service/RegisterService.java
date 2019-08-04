package cn.oveay.cspuzzle.service;

import cn.ovea_y.puzzle.bean.User;

public interface RegisterService {
    User register(User user);
    boolean check(User user);
}
