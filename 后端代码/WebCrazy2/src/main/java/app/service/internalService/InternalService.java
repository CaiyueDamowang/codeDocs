package app.service.internalService;

import app.pojo.internal.Internal;

import java.util.List;

/**
 * @author myk
 * @description: TODO
 * @date 2020/12/21 16:30
 */
public interface InternalService {
    List<Internal> getInternal(int pageNo);
}
