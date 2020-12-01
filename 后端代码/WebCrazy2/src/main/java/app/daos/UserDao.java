package app.daos;

import app.pojo.userservice.User;
import app.service.dataService.DataService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Fizz Pu
 * @Date 2020/11/2 下午10:55
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class UserDao {

    @Autowired
    DataService dataService;

    // 通过邮箱查找用户
    public User findUserByEmail(String email){
        String hql = "from User where email = :email";
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery(hql);
        query.setParameter("email", email);
        User res = query.uniqueResult();
        tx.commit();
        session.close();
        return res;
    }

    // 保存用户
    public void saveUser(User user){
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }

    // 通过id查找用户
    public User findUserById(Long  userId){
        Session session = dataService.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "from User where userId = :userId";
        Query<User> query = session.createQuery(hql);
        query.setParameter("userId", userId);
        User res = query.uniqueResult();
        tx.commit();
        return res;
    }

}
