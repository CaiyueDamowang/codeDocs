package app.service.userservice;


import app.pojo.userservice.User;
import app.service.dataService.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author Fizz Pu
 * @Date 2020/10/4 下午2:22
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class UserService implements AbstractUserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataService dataService;

    @Override
    public User login(User loginForm){
        return null;
    }

    public User searchUserByEmail(String email) {
        return null;
    }

    public User register(User user){
        return null;
    }


    @Override
    public User logout() {
        return null;
    }

    @PostConstruct
    void init(){
        logger.info("userService组件初始化成功");
    }
}

