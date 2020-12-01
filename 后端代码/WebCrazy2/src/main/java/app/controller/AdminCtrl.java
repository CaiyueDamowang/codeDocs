package app.controller;

import app.pojo.OrdRes;
import app.pojo.post.PostInfo;
import app.supported.annotations.Admin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/11/26 下午10:43
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@RestController
@RequestMapping(produces = "application/json;charset=utf8",consumes = "application/json")
public class AdminCtrl {

    /**
     * 审核帖子
     * @param postId 帖子id
     * @param action 0 : 审核不通过, 1: 审核通过
     * @return
     */
    @Admin
    @RequestMapping(value = "/cumt/web/admin/checkPost", method =  RequestMethod.GET)
    public OrdRes checkPost(@RequestParam int postId, @RequestParam int action){
        return null;
    }

    /**
     * 获得未审核的帖子
     * @param start 开始
     * @param end 结束
     * @return 帖子
     */
    @Admin
    @RequestMapping(value = "/cumt/web/admin/uncheck/{start}/{end}", method = RequestMethod.GET)
    public List<PostInfo> getUncheckPost(@PathVariable int start, @PathVariable int end){
        return null;
    }
}
