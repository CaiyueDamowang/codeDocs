package app.service.userservice;


import app.daos.UserDao;
import app.pojo.LoginResponse;
import app.pojo.OrdRes;
import app.pojo.userservice.User;
import app.service.dataService.DataService;
import app.service.encryption.HashTools;
import app.service.encryption.Jwt;
import app.utils.Generator;
import org.aspectj.weaver.ast.Or;
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
    UserDao userDao;

    @Autowired
    Jwt jwtServcie;

    @Override
    public OrdRes login(User loginForm){
        checkUser(loginForm);
        String hashPsw = HashTools.hashPsw(loginForm.getPassWord());
        User curUser = userDao.findUserByEmail(loginForm.getEmail());

        if(curUser == null)return new LoginResponse(1, "未注册", null, null);
        if (!hashPsw.equals(curUser.getPassWord())) return new LoginResponse(2, "密码或者邮箱错误", null, null);

        // 登录成功, 签发token和refreshToken
        Long userId = curUser.getUserId();
        String token = jwtServcie.getToken(userId);
        return new LoginResponse(200, "登录成功", token, null);
    }

    @Override
    public OrdRes register(User user){
        checkUser(user);
        User curUser = userDao.findUserByEmail(user.getEmail());
        if(curUser != null) return new OrdRes(1, "邮箱已被注册");
        user.setUserName(Generator.defaultUserName(user.getEmail())); // 生成随机用户名
        user.setIsSuperUser(0); // 设置用户权限, 默认是普通用户
        user.setPassWord(HashTools.hashPsw(user.getPassWord())); // hash密码
        userDao.saveUser(user);
        return new OrdRes(200, "注册成功");
    }

    private void checkUser(User loginForm){
        if(loginForm == null) throw new  IllegalArgumentException("User为null");
        if(loginForm.getEmail() == null) throw new  IllegalArgumentException("User.email为null");
        if(loginForm.getPassWord() == null) throw new  IllegalArgumentException("User.passWord为null");
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

