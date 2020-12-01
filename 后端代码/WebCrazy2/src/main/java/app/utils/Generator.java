package app.utils;

/**
 * @Author Fizz Pu
 * @Date 2020/11/4 下午10:51
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class Generator {
    public static String defaultUserName(String email){
        return "游客" + email;
    }

    public static String imageBaseUrl(Long imageId){ return "http:localhost:90/web/crazy/images/" + imageId;}
}
