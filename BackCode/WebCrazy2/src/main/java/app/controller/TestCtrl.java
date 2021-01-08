package app.controller;

import app.pojo.Kaptcha;
import app.pojo.OrdRes;
import app.service.encryption.Base64;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author Fizz Pu
 * @Date 2020/12/22 下午4:07
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Controller
@CrossOrigin // 允许跨域
public class TestCtrl {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("/testTomcat")
    void testTomcat(HttpServletResponse response) throws IOException {
        // response.setContentType("html/text");
        System.out.println("jfjsdafjjsfdjsidahsufafhusdfhsau");
        Writer writer = response.getWriter();
        writer.write("hello world");
        writer.flush();
    }

    /**
     * 验证码接口
     */
    @RequestMapping(value = "/web/crazy/kapt", method = RequestMethod.GET)
    @ResponseBody
    public Kaptcha getKap(HttpSession httpSession, @RequestParam String sid) throws IOException {
        // 拼一个img标签出来
        // <img src = "data:image/jepg;base64," + base64str + >

        Kaptcha kaptcha = new Kaptcha();
        String text = defaultKaptcha.createText();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(defaultKaptcha.createImage(text), "jpg", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String base64 = Base64.byte64ToString(bytes);
        String html = "<img src=\"data:image/jpg;base64," + base64 + "\"" + "/>";
        kaptcha.setData(html);
        kaptcha.setCode(200);
        httpSession.setAttribute(sid, text);
        return kaptcha;
    }
}
