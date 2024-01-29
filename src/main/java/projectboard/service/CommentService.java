package projectboard.service;

import projectboard.domain.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long id);
    List<Comment> findByPostId(Long id);
    List<Comment> findByUserId(Long id);
}
