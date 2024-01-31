package projectboard.service;

import projectboard.domain.Post;
import projectboard.dto.post.CreatePostReqDto;
import projectboard.dto.post.UpdatePostDto;

import java.util.List;

public interface PostService {
    void createPost(Long userId, CreatePostReqDto createPostReqDto);
    void updatePost(Long id, UpdatePostDto updatePostDto);
    void deletePost(Long id);
    Post findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByContent(String content);
    List<Post> findByTagName(String tagName);
    List<Post> findByUserName(String userName);
}
