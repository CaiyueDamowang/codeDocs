package app.controller;

import app.pojo.OrdRes;
import app.pojo.post.Comment;
import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;
import app.pojo.userservice.User;
import app.service.postservice.CommentService;
import app.service.timeservice.CurTime;
import jdk.net.SocketFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
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

    @PostConstruct
    void init(){
        logger.info("LoginControl初始化成功");
    }

    /**-----------------------------登录,注册----------------------------*/
    @RequestMapping(value = "/cumt/web/login", method = RequestMethod.POST, consumes = "application/json")
    public String getLogin(@RequestBody User form) {
        return null;
    }

    @RequestMapping(value = "/cumt/web/register", method = RequestMethod.POST)
    public String getRegister(User form) { // 加上@RequestBody就报错，我也是醉了
        return null;
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

    /**---------------------------图片上传------------------------------------------*/
    @RequestMapping(value = "/web/crazy/image/bybase64", method = RequestMethod.POST)
    public OrdRes upLoad(List<PostImage> imageData){
        return null;
    }

    @RequestMapping(value = "/web/crazy/image/bybase64", method = RequestMethod.POST)
    public OrdRes getImgOfPost(int PostId){
        return null;
    }

    /**-----------------------------评论---------------------- -------------*/
    @RequestMapping(value = "/web/crazy/comment", method = RequestMethod.GET)
    public List<Comment> getComment(@RequestParam("postId") int postId){
        //1. 定义拦截器，验证token
        // /web/crazy/comment?post=1
        return commentService.getAllComment(postId);
    }

    @RequestMapping(value = "/web/crazy/comment", method = RequestMethod.POST)
    public OrdRes saveComment(Comment comment){
        try {
            comment.setCreateTime(timeService.getCurTime());
            commentService.saveComment(comment);
        } catch (Exception ex){
            logger.error("/web/crazy/comment method = post, 请求异常,获得帖子失败,详细信息:", ex);
            return  new OrdRes(1, "服务器内部错误");
        }
        return new OrdRes(0, "保存成功");
    }
}

