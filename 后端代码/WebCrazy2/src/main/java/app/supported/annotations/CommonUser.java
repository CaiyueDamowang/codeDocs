package app.supported.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Fizz Pu
 * @Date 2020/11/26 上午10:34
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// 定义普通用户注解

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommonUser {
}
