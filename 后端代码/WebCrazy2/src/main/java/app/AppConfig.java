package app;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Author Fizz Pu
 * @Date 2020/10/4 上午12:52
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// java代码进行配置
@Configuration
public class AppConfig {

    /**
     * 创建第三方Bean,函数返回值自动装入ioc容器
     * @return SessionFactory
     */
    @Bean(name="Hibernate")
    public SessionFactory createSessionFactory(){
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();
        return config.configure().buildSessionFactory();
    }

    /**
     * 处理前端通过form data二进制流的方式提交的数据
     * @return CommonsMultipartResolver
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver createMulitipartResolver(){
        return new CommonsMultipartResolver();
    }
}

