package app.service.encryption;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Fizz Pu
 * @Date 2020/10/6 下午7:44
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


public class HashTools {

    /**
     * 采用
     * @param psw
     * @return
     */
    static final SecretKey key;
    static final  String hashScret = "1cdA31sdsdwfw12343dsAQWsaa@1da";
    static{
        byte[] bytes = hashScret.getBytes(StandardCharsets.UTF_8);
        key = new SecretKeySpec(bytes,  "MD5");
    }

    public static String hashPsw(String psw) {
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(key);
            mac.update(psw.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, mac.doFinal()).toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException ex){
            throw new RuntimeException("密码hash错误");
        }
    }
}

