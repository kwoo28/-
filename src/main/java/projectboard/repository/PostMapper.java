package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.Post;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    int createPost(@Param("post")Post post);
    int updatePost(@Param("post")Post post);
    int deletePost(@Param("id") Long id);
    Optional<Post> findPostById(@Param("id") Long id);
    List<Post> findPostByTitle(@Param("title") String title);
    List<Post> findPostByContent(@Param("content") String content);
    List<Post> findPostByTagId(@Param("tagId") Long tagId);
    List<Post> findPostByUserId(@Param("id") Long id);
}
