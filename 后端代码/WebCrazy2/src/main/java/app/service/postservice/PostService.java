package app.service.postservice;


import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午4:47
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

public interface PostService {

    /**
     * 获得帖子，按时间排序，从pageIndex开始，每次拿count个
     * @param start
     * @param count
     * @return
     */
    List<PostInfo> getPostInfo(Integer start, Integer count);

    /**
     * 获得帖子的图片
     * @param postId
     * @return
     */
    List<PostImage> getPostImageBaseString(Long postId);

    /**
     * 保存帖子
     * @param postInfo
     * @return
     */
    Map<String, String> upLoadPost(PostInfo postInfo);

    /**
     * 根据图片id,获得图片
     * @param imageId
     * @return
     */
    PostImage getImage(Long imageId);
}
