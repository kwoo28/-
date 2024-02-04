package projectboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import projectboard.domain.Comment;
import projectboard.dto.comment.CreateCommentReqDto;
import projectboard.dto.comment.UpdateCommentReqDto;
import projectboard.exception.CommentNotFoundException;
import projectboard.exception.PostNotFoundException;
import projectboard.exception.UserNotFoundException;
import projectboard.repository.CommentMapper;
import projectboard.repository.PostMapper;
import projectboard.repository.UserMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    @Override
    public void createComment(Long userId, CreateCommentReqDto createCommentReqDto) {

        postMapper.findPostById(createCommentReqDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("해당 게시글 없음"));

        userMapper.findUserById(userId).orElseThrow(() -> new UserNotFoundException("해당 유저 없음"));

        Comment comment = Comment.builder().
                postId(createCommentReqDto.getPostId()).
                userId(userId).
                content(createCommentReqDto.getContent()).
                build();

        commentMapper.createComment(comment);
    }

    @Override
    public void updateComment(Long id, UpdateCommentReqDto updateCommentReqDto) {
        commentMapper.findCommentById(id).orElseThrow(() -> new CommentNotFoundException("해당 댓글 없음"));

        Comment comment = Comment.builder().
                id(id).
                content(updateCommentReqDto.getContent()).
                build();

        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentMapper.findCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("해당 댓글 없음"));

        commentMapper.deleteComment(id);
    }

    @Override
    public List<Comment> findByPostId(Long id) {
        postMapper.findPostById(id)
                .orElseThrow(() -> new PostNotFoundException("해당 게시글 없음"));

        return commentMapper.findCommentByPostId(id);
    }

    @Override
    public List<Comment> findByUserId(Long id) {
        userMapper.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 유저 없음"));

        return commentMapper.findCommentByUserId(id);
    }
}
