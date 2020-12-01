package app.controller;

import app.pojo.OrdRes;
import app.pojo.userservice.User;
import app.supported.annotations.Root;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/11/26 下午10:43
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class RootCtrl {

    /**
     * 添加管理员帐号
     * @param email 用户邮箱
     * @return
     */
    @Root
    @RequestMapping(value = "/cumtWeb/addAdmin", method = RequestMethod.GET)
    public OrdRes addAdmin(@RequestParam String email){
        return null;
    }

    /**
     * 获得管理员信息
     * @return  数组
     */
    @Root
    @RequestMapping(value = "/cumt/Web/getAdmin", method = RequestMethod.GET)
    public List<User> getAdmin(){
        return null;
    }

    /**
     * 撤销管理员权限
     */
    @Root
    @RequestMapping(value = "/cumt/Web/deleteAdmin", method = RequestMethod.GET)
    public OrdRes deleteAdmin(){
        return null;
    }
}
