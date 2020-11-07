package app.controller;

import app.pojo.OrdRes;
import app.pojo.post.Comment;
import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;
import app.pojo.userservice.User;
import app.service.postservice.CommentService;
import app.service.postservice.PostService;
import app.service.timeservice.CurTime;
import app.service.userservice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/5 下午5:13
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */


// 1.路径写在类上面，就不可达了
// 2. 参数或取失败
// 3. 请求与响应乱码问题

@RestController
@RequestMapping(produces = "application/json;charset=utf8")
public class RestCtrl {
    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        logger.info("LoginControl初始化成功");
    }

    /**-----------------------------登录,注册----------------------------*/
    @RequestMapping(value = "/cumt/web/login", method = RequestMethod.POST, consumes = "application/json")
    public OrdRes getLogin(User form) {
        return userService.login(form);
    }

    @RequestMapping(value = "/cumt/web/register", method = RequestMethod.POST)
    public OrdRes getRegister(User form) {
        return userService.register(form);
    }

    /**--------------------------------帖子---------------------------------------*/
    @RequestMapping(value = "/web/crazy/post", method = RequestMethod.POST)
    public OrdRes uploadPost(PostInfo postInfo) {
        return null;
    }

    @RequestMapping(value = "/web/crazy/post", method = RequestMethod.GET)
    public PostInfo getPost(@RequestParam int start, @RequestParam int count){
        return null;
    }

    /**
     * 获得帖子下图片的链接
     * @param PostId
     * @return
     */
    @RequestMapping(value = "/web/crazy/image/bybase64/{postId}", method = RequestMethod.POST)
    public List<PostImage> getImgOfPost(Long PostId){
        return null;
    }

    /**
     * 获得图片, 实际上就是向客户输出二进制流
     */
    @RequestMapping(value = "/web/crazy/images/{imageId}")
    public void getImage(@PathVariable Long imageID){
        if(imageID == null) throw  new IllegalArgumentException("参数异常");
        PostImage image = postService.getImage(imageID);
        // 解析baseStr, 输出二进制流到客户端, base64的长度,mysql的类型选择
    }

    /**-----------------------------评论---------------------- -------------*/
    /**
     * 获得评论
     * @param postId
     * @return
     */
    @RequestMapping(value = "/web/crazy/comment", method = RequestMethod.GET)
    public List<Comment> getComment(@RequestParam("postId") int postId){
        return commentService.getAllComment(postId);
    }

    /**
     * 上传评论
     * @param comment
     * @return
     */
    @RequestMapping(value = "/web/crazy/comment", method = RequestMethod.POST)
    public OrdRes saveComment(Comment comment){
        comment.setCreateTime(timeService.getCurTime());
        commentService.saveComment(comment);
        return new OrdRes(0, "保存成功");
    }
}

