package app.service.postservice;


import app.daos.CommentDao;
import app.pojo.post.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @Author Fizz Pu
 * @Date 2020/10/15 下午8:56
 * @Version 1.0
 * 失之毫厘，缪之千里！
 */

@Component
public class CommentServiceImp implements CommentService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    CommentDao commentDataService;

    @Override
    public List<Comment> getAllComment(int postId) {
        List<Comment> res = new ArrayList<>();
        List<Integer> roots = commentDataService.getFirstCommentsId(postId); // 所有的一级评论的id
        if(roots == null)return res;

        for(Integer rootId: roots){
            List<Comment> comments = commentDataService.getComments(rootId, postId); //一级评论下所有的评论
            res.add(generatorTree(comments));
        }

        return  res;
    }

    // 扁平化数组树形化
    Comment generatorTree(List<Comment> comments){
        // 参数检查
        if(comments == null)return null;

        Comment root = null; // 树根
        Map<Integer, Comment> map = new HashMap<>();

        for(Comment comment : comments) {
            map.put(comment.getCommentId(), comment); // 提前加入map中, 便于后面由parentId找到对应的comment
        }

        // 遍历map, 并将子节点加到父节点的集合中
        Set<Integer> keys = map.keySet();
        for(Integer key : keys){
            Comment currentNode = map.get(key);
            Integer parentId = currentNode.getParentId();//通过parentId到map中搜索对应的comment类
            Comment parentNode = map.get(parentId);
            if(parentNode == null) root = currentNode;
            if(parentNode != null && parentNode.getChildren() == null){ parentNode.setChildren(new ArrayList<>());}
            if(parentNode != null){parentNode.getChildren().add(currentNode);}
        }

        return  root;
    }

    @Override
    public boolean saveComment(Comment comment) throws Exception {
        Integer applyId = comment.getParentId();
        try {
            if (applyId == null) { // 是对帖子的评论
                commentDataService.saveUpdateComment(comment);
            } else { // 是对帖子的回复
                commentDataService.saveComment(comment);
            }
        } catch (Exception exception){
            throw  exception;
        } return true;
    }

    @PostConstruct
    void init(){
        logger.info("CommentService组件初始化成功");
    }
}
