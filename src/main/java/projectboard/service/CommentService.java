package projectboard.service;

import projectboard.domain.Comment;
import projectboard.dto.comment.CreateCommentReqDto;
import projectboard.dto.comment.UpdateCommentReqDto;

import java.util.List;

public interface CommentService {
    void createComment(Long userId, CreateCommentReqDto createCommentReqDto);
    void updateComment(Long id, UpdateCommentReqDto updateCommentReqDto);
    void deleteComment(Long id);
    List<Comment> findByPostId(Long id);
    List<Comment> findByUserId(Long id);
}
