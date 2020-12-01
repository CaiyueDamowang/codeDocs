package app.supported.interceptor;

import app.daos.UserDao;
import app.pojo.userservice.User;
import app.supported.Holder;
import app.supported.annotations.Admin;
import app.supported.annotations.CommonUser;
import app.supported.annotations.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * @Author Fizz Pu
 * @Date 2020/11/26 上午10:58
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class CheckIdentity implements HandlerInterceptor {

    @Autowired
    UserDao userDao;

    @Autowired
    Holder holder;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        CommonUser commonUser = null;
        Admin admin = null;
        Root root = null;

        if(handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            if (method != null) {
                commonUser = method.getAnnotation(CommonUser.class);
                admin = method.getAnnotation(Admin.class);
                root = method.getAnnotation(Root.class);
            }
        }

        User user = userDao.findUserById(holder.getUser());
        boolean isAdmin = (user != null && user.getIsSuperUser() == 1);
        boolean isRoot = (user != null && user.getIsSuperUser() == 2);
        Writer writer = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json");

        // 优先校验root用户
        if(root != null) {
            if (isRoot) return true;
            writer.write("{\"code\":500, msg: \"没有root用户权限\"}");
            return false;
        }

        // 校验Admin
        if(admin != null) {
            if (isAdmin) return true;
            writer.write("{\"code\":500, msg: \"没有管理员权限\"}");
            return false;
        }

        if(commonUser == null) {
            writer.write("{\"code\":500, msg: \"没有普通用户权限\"}");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
