package  app.service.postservice;

import app.daos.CommentDao;
import app.daos.PostInfoDao;
import app.pojo.post.PostImage;
import app.pojo.post.PostInfo;
import app.service.SensitiveWorldFilter;
import app.supported.Holder;
import app.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Fizz Pu
 * @Date 2020/10/23 下午4:47
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class PostServiceImp implements PostService {

    private final String REPLACE_WORD = "**";

    @Autowired
    PostInfoDao postInfoDao;

    @Autowired
    Holder holder;

    @Autowired
    SensitiveWorldFilter sensitiveWorldFilter;

    @Override
    public List<PostInfo> getPostInfo(Long start, Long count) {
        List<PostInfo> posts = postInfoDao.getPostInfo(start, count);
        for(PostInfo post : posts){
            List<PostImage> images = postInfoDao.getPostImages(post.getPostId());
            post.setPostImages(images);
            post.setContent(sensitiveWorldFilter.filter(post.getContent(), REPLACE_WORD));
            post.setTitle(sensitiveWorldFilter.filter(post.getTitle(), REPLACE_WORD));
        }
        return posts;
    }

    @Override
    public List<PostImage> getPostImageBaseString(Long postId) {
        return  postInfoDao.getPostImages(postId);
    }

    @Override
    public Map<String, String> upLoadPost(PostInfo postInfo) {

        Map<String, String> map = new HashMap<>();

        if(postInfo == null)throw new IllegalArgumentException("postInfo为null");
        if(postInfo.getTitle() == null){
            map.put("msg", "上传失败,title不能为空");
            return map;
        }

        // 1. 保存帖子
        postInfo.setUserId(holder.getUser());
        Long postId = postInfoDao.savePost(postInfo);
        // 2. 保存图片
        List<PostImage> postImages = postInfo.getPostImages();
        // 生成参数, url
        Long imageId;
        if(postImages != null && postImages.size() > 0){
            for(PostImage image : postImages){
                image.setPostId(postId);
                imageId = postInfoDao.saveImage(image);
                image.setImgUrl(Generator.imageBaseUrl(imageId));
                postInfoDao.update(image);
            }
        }

        map.put("msg","上传成功");
        return map;
    }

    @Override
    public PostImage getImage(Long imageId) {
        return postInfoDao.getImage(imageId);
    }
}