package app.daos;


import app.pojo.post.PostInfo;

import java.util.List;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午4:42
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */
public class PostInfoDao {

    /**
     * 分页查询帖子
     * @param start 开始位置
     * @param count 查找count条帖子
     * @return
     */
    public List<PostInfo>  getPostInfo(Integer start, Integer count){
        return  null;
    }

    /**
     * 保存帖子
     * @param post
     */
    public void savePost(PostInfo post){

    }
}
