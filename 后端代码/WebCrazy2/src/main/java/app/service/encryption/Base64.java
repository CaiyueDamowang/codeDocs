package app.service.encryption;


/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午10:03
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class Base64 {

    /**
     * base64转成byte[]数组
     * @param base64Str
     * @return
     */
    public static byte[] base64ToByte(String base64Str){
        return java.util.Base64.getDecoder().decode(base64Str);
    }

    /**
     * byte[]数组转String
     * @param bytes
     * @return
     */
    public static String byte64ToString(byte[] bytes){
        return java.util.Base64.getEncoder().withoutPadding().encodeToString(bytes);
    }
}

