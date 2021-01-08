package app.service.dataService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Fizz Pu
 * @Date 2020/12/31 上午12:33
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class MybaitsDataService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
