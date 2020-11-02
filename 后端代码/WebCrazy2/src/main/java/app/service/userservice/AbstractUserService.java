package app.service.userservice;

import app.pojo.userservice.User;

/**
 * @Author Fizz Pu
 * @Date 2020/10/4 上午12:51
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public interface AbstractUserService {

    /**
     * 用户登出
     * @return User对象
     */
    User logout();

    /**
     * 注册
     * @return User对象
     */
    User register(User user);


    /**
     * 用户登录
     * @return User对象
     */
    User login(User loginForm);

    /**
     * 根据用户邮箱查找用户
     * @param email 根据用户邮箱查找用户
     * @return User对象
     */
    public User searchUserByEmail(String email);

}

