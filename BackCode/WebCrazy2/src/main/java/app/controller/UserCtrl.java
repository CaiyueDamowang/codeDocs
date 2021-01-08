package app.controller;

import app.pojo.OrdRes;
import app.pojo.post.Comment;
import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;
import app.pojo.userservice.User;
import app.service.encryption.Base64;
import app.service.postservice.CommentService;
import app.service.postservice.PostService;
import app.service.timeservice.CurTime;
import app.service.userservice.UserService;
import app.supported.Holder;
import app.supported.annotations.CommonUser;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author Fizz Pu
 * @Date 2020/10/5 下午5:13
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


// 1.路径写在类上面，就不可达了
// 2. 参数或取失败
// 3. 请求与响应乱码问题
// 4. 统一用json处理数据


@Controller
@RequestMapping(produces = "application/json;charset=utf8",consumes = "application/json")
public class UserCtrl {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Holder holder;

    @Autowired
    CommentService commentService;

    @Autowired
    CurTime timeService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @PostConstruct
    void init(){
        logger.info("UserCtrl初始化成功");
    }

    /**-----------------------------登录,注册----------------------------*/
    @ResponseBody
    @RequestMapping(value = "/cumt/web/login", method = RequestMethod.POST)
    public OrdRes getLogin(@RequestBody User form) {
        return userService.login(form);
    }

    @ResponseBody
    @RequestMapping(value = "/cumt/web/register", method = RequestMethod.POST)
    public OrdRes getRegister(@RequestBody  User form) {
        return userService.register(form);
    }

    /**--------------------------------帖子---------------------------------------*/
    // 上传帖子
    @ResponseBody
    @RequestMapping(value = "/web/crazy/post", method = RequestMethod.POST)
    public Map<String, String> uploadPost(@RequestBody PostInfo postInfo) {
        Long userId = holder.getUser();

        if(userId == null){
            userId = 1L;
        }

        postInfo.setUserId(userId);

        return postService.upLoadPost(postInfo);
    }

    // 获得帖子
    @RequestMapping(value = "/web/crazy/post", method = RequestMethod.GET)
    public List<PostInfo> getPost(@RequestParam int pageIndex, @RequestParam int count){
        return postService.getPostInfo(pageIndex, count);
    }

    /**
     * 获得帖子下图片的链接
     * @param PostId
     * @return
     */
    @CommonUser
    @RequestMapping(value = "/web/crazy/image/bybase64/{postId}", method = RequestMethod.POST)
    public List<PostImage> getImgOfPost(Long PostId){
        return null;
    }

    /**
     * 获得图片, 实际上就是向客户输出二进制流
     */
    @CommonUser
    @RequestMapping(value = "/web/crazy/images/{imageId}")
    public OrdRes getImage(@PathVariable Long imageID, HttpServletResponse response){
        if(imageID == null) throw  new IllegalArgumentException("参数异常");
        PostImage image = postService.getImage(imageID);
        if(image == null){
            return new OrdRes(0, "图片不存在");
        }
        // 解析baseStr, 输出二进制流到客户端, base64的长度,mysql的类型选择
        String baseStr;
        String fileName;
        String suffix;

        fileName = image.getFileName();
        suffix = fileName.substring(fileName.lastIndexOf("."));
        response.setContentType("image/" + suffix);
        baseStr = image.getBaseStr();
        try(OutputStream outputStream = response.getOutputStream()) {
            // Writer writer = response.getWriter(); 字符流, 此处要用字节流
            outputStream.write(Base64.base64ToByte(baseStr));
            outputStream.flush();
        } catch (IOException ioException) {
            logger.error(ioException.getMessage());
        }

        return new OrdRes(1, "图片返回完毕");
    }

    /**-----------------------------评论---------------------- -------------*/
    /**
     * 获得评论
     * @param postId
     * @return
     */
    @CommonUser
    @RequestMapping(value = "/web/crazy/comment", method = RequestMethod.GET)
    public List<Comment> getComment(@RequestParam("postId") int postId){
        return commentService.getAllComment(postId);
    }

    /**
     * 上传评论
     * @param comment
     * @return
     */
    @CommonUser
    @RequestMapping(value = "/web/crazy/comment", method = RequestMethod.POST)
    public OrdRes saveComment(Comment comment){
        comment.setCreateTime(timeService.getCurTime());
        commentService.saveComment(comment);
        return new OrdRes(0, "保存成功");
    }

    /**
     * 点赞
     */
    @RequestMapping(value = "/web/crazy/like", method = RequestMethod.GET)
    public OrdRes like(Comment comment){
        return null;
    }


}



