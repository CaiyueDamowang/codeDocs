package app.supported;

import app.pojo.userservice.User;
import org.springframework.stereotype.Component;

/**
 * @Author Fizz Pu
 * @Date 2020/11/24 下午10:23
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

/**
 * {"thread1":user1, "thread2": user2}
 */

@Component
public class Holder {
    private ThreadLocal<Long> userIds = new ThreadLocal<>();

    public Long getUser(){return userIds.get();}

    public void setUser(Long id){userIds.set(id);}

    public void clear(){
        userIds.remove();
    }
}
