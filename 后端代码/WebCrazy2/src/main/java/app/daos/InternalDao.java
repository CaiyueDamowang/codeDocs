package app.daos;

import app.pojo.internal.Internal;
import app.service.dataService.DataService;
import app.service.internalService.InternalServiceImpl;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author myk
 * @description: TODO
 * @date 2020/12/20 16:04
 */
@Component
public class InternalDao {
    @Autowired
    DataService dataService;



    //分页查询
    public List<Internal> getAllInternal(int pageNo,int pageSize){
        Session  session = dataService.getSession();
        String hql = "from Internal";
        Query<Internal> query = session.createQuery(hql);
        query.setFirstResult((pageNo-1)*pageSize);
        query.setMaxResults(pageSize);
        List<Internal> list = query.list();
        return  list;
    }

    //根据内推ID查找内推信息
    public Internal findInternalByID(int id){
        Session session = dataService.getSession();
        String hql = "from Internal where id = ? ";
        Query<Internal> query =  session.createQuery(hql);
        query.setParameter(0,id);
        Internal internal = query.uniqueResult();
        session.beginTransaction().commit();
        session.close();
        return internal;
    }

    //根据内推人姓名查找内推信息
    public List<Internal> findInternalByName(String name){
        Session session = dataService.getSession();
        String hql = "from Internal where name = ?";
        Query<Internal> query = session.createQuery(hql);
        query.setParameter(0,name);
        List<Internal> list = query.list();
        session.beginTransaction().commit();
        session.close();
        return list;
    }

    //根据部分内推简介查询内推信息
    public List<Internal> findInternalByMsg(String temp){
        Session session = dataService.getSession();
        String hql = "from Internal where Msg = ?";
        List<Internal> list = session.createQuery(hql).setParameter(0,"%" + hql + "%").list();
        session.beginTransaction().commit();
        session.close();
        return list;
    }

    public Internal findInternalByLink(String link){
        Session session = dataService.getSession();
        String hql = "from Internal where link = ?";
        Query<Internal> query = session.createQuery(hql).setParameter(0,link);
        Internal internal = query.uniqueResult();
        session.beginTransaction().commit();
        session.close();
        return internal;
    }

    //时间戳： yyyy-MM-dd
    public List<Internal> findInternalByDate(String startDate , String endDate) throws ParseException {
        Session session = dataService.getSession();
        String sql = "select * from internal_tb where internal_startTime between ? and ? ";
        List list;
        if(endDate != null) {
            list = session.createSQLQuery(sql).addEntity(Internal.class).setParameter(0, startDate).setParameter(1, endDate).list();
        } else{
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(date);
            list = session.createSQLQuery(sql).addEntity(Internal.class).setParameter(0, startDate).setParameter(1, s).list();
        }
        session.beginTransaction().commit();
        session.close();
        return list;
    }

    //保存内推信息
    public void add(Internal internal){
        Session session = dataService.getSession();
        session.save(internal);
        session.beginTransaction().commit();
        session.close();
    }

    //更新内推信息
    public void update(Internal internal){
        Session session = dataService.getSession();
        session.update(internal);
        session.beginTransaction().commit();
        session.close();
    }

    //删除
    public int delete(Internal internal){
        Session session = dataService.getSession();
        String hql = "delete from Internal where id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,internal.getId());
        int res = query.executeUpdate();
        session.beginTransaction().commit();
        session.close();
        return res;
    }

}
