package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    int createComment(@Param("comment") Comment comment);
    int updateComment(@Param("comment") Comment comment);
    int deleteComment(@Param("id") Long id);
    Comment findCommentById(@Param("id") Long id);
    List<Comment> findCommentByPostId(@Param("id") Long id);
    List<Comment> findCommentByUserId(@Param("id") Long id);
}
