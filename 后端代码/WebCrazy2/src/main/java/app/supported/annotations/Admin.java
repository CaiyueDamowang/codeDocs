package app.supported.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Fizz Pu
 * @Date 2020/11/26 上午11:46
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

// 管理员权限

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Admin {
}
