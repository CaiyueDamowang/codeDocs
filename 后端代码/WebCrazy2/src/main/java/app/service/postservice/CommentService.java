package app.service.postservice;


import app.pojo.post.Comment;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/15 下午8:56
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public interface CommentService {

    /**
     * 获得所有的评论
     * @param postId
     * @return
     */
    List<Comment> getAllComment(int postId);

    /**
     * 保存评论，并返回该贴子下的所有评论，避免两次请求
     * @param comment
     * @return
     */
    public boolean saveComment(Comment comment) throws Exception;

}
