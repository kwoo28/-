package projectboard.service;

import projectboard.domain.Post;
import projectboard.domain.Tag;

import java.util.List;

public interface PostService {
    void createPost(Post post, List<String> tagNames);
    void updatePost(Post post);
    void deletePost(Long id);
    Post findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByContent(String content);
    List<Post> findByTagName(String tagName);
    List<Post> findByUserName(String userName);
}
