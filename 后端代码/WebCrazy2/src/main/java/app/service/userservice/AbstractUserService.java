package app.service.userservice;

import app.pojo.OrdRes;
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
    OrdRes register(User user);


    /**
     * 用户登录
     * @return 提示信息
     */
    OrdRes login(User loginForm);


}

