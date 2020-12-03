package app.utils;

import app.service.SensitiveWorldFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author Fizz Pu
 * @Date 2020/12/3 下午11:15
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
class SensitiveWorldFilterTest {

    static SensitiveWorldFilter sensitiveWorldFilter;

    @BeforeAll
    static void setUp() throws IOException {
        sensitiveWorldFilter = new SensitiveWorldFilter("sensitive-word.txt");
    }

    @Test
    void filter() throws IOException {
        System.out.println(sensitiveWorldFilter.filter("卖淫我取返回粟,你到也,地方哈市方式,发生地撒", "**"));
        System.out.println(sensitiveWorldFilter.filter("卖淫我取返回粟, hhAV你到也,地方哈市方式,发生地撒", "**"));
        System.out.println(sensitiveWorldFilter.filter("卖淫我取返回嫖娼粟,你 就复发到也,地方哈市方式,发生地撒", "**"));
        System.out.println(sensitiveWorldFilter.filter("卖淫我取返回粟,你到也,地方哈赌博市方式,发生地撒", "**"));
        System.out.println(sensitiveWorldFilter.filter("卖淫我取返回粟,你到也,地方哈赌博市方吸食鸦片式,发生地撒", "**"));
        System.out.println(sensitiveWorldFilter.filter(" 我吸毒", "**"));
        System.out.println(sensitiveWorldFilter.filter(null, "**"));
        System.out.println(sensitiveWorldFilter.filter("我是邹杰的爸爸", "**"));
    }



    void filter(String txt){

    }
}