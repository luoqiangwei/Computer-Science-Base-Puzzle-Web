package cn.ovea_y.puzzle.service;

import cn.ovea_y.puzzle.bean.User;

public interface RegisterService {
    User register(User user);
    boolean check(User user);
}
