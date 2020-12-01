package app.supported.interceptor;

import app.daos.UserDao;
import app.service.encryption.Jwt;
import app.supported.Holder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * @Author Fizz Pu
 * @Date 2020/11/24 下午10:52
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class GetIdInterceptor implements HandlerInterceptor {
    static private final Logger logger = LoggerFactory.getLogger(GetIdInterceptor.class);

    @Autowired
    private Jwt jwt;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Holder holder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 从请求头里获得token
        String token = httpServletRequest.getHeader("token");
        Long userId = null;
        boolean isAllow = false;

        if(token != null){
            userId = jwt.getId(token);
            isAllow = jwt.verify(token);
        }

        if(token == null || !isAllow || userId == null){
            Writer writer  = httpServletResponse.getWriter();
            httpServletResponse.setContentType("application/json");
            writer.write("{\"code\":500, \"msg\": \"token解析异常\"}");
            return  false;
        }

        // 拿出UserId放在当前线程里面
        holder.setUser(userId); // 对象和当前线程绑定起来, 用来代替session
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        holder.clear();
    }
}
