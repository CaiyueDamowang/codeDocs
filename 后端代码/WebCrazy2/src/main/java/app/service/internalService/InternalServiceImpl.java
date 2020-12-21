package app.service.internalService;

import app.daos.InternalDao;
import app.pojo.internal.Internal;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author myk
 * @description: TODO
 * @date 2020/12/21 16:53
 */
public class InternalServiceImpl implements InternalService{

    @Autowired
    InternalDao internalDao;

    @Override
    public List<Internal> getInternal(int pageNo) {
        return internalDao.getAllInternal(pageNo);
    }

}
