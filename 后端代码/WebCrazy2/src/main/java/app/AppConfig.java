package app;

import app.supported.interceptor.GetIdInterceptor;
import app.supported.interceptor.CheckIdentity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;


/**
 * @Author Fizz Pu
 * @Date 2020/10/4 上午12:52
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// java代码进行配置
@Configuration
@EnableWebMvc
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

    /**
     * 使用java配置springmvc的监听器, 当然, 也可以使用配置文件进行配置
     */

    @Autowired
    GetIdInterceptor getIdInterceptor;

    @Autowired
    CheckIdentity checkIdentity;

    @Bean
    public WebMvcConfigurer configWebMvc(){
        return new WebMvcConfigurerAdapter(){
            @Override
            public void addInterceptors(InterceptorRegistry interceptorRegistry) {
                interceptorRegistry.addInterceptor(getIdInterceptor).addPathPatterns("/**").
                        excludePathPatterns("/cumt/web/login").excludePathPatterns("/cumt/web/register");

                interceptorRegistry.addInterceptor(checkIdentity).addPathPatterns("/**").excludePathPatterns("/").
                        excludePathPatterns("/cumt/web/login").excludePathPatterns("/cumt/web/register");
            }
        };
    }
}

