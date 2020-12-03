package app.supported.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Fizz Pu
 * @Date 2020/12/3 下午5:58
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogRequired {

}
