package projectboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import projectboard.domain.Comment;
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
    public void createComment(Comment comment) {
        if(postMapper.findPostById(comment.getPostId())==null){
            throw new PostNotFoundException("해당 게시글 없음");
        }
        if(userMapper.findUserById(comment.getUserId())==null){
            throw new UserNotFoundException("해당 유저 없음");
        }
        commentMapper.createComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        if(commentMapper.findCommentById(comment.getId())==null){
            throw new CommentNotFoundException("해당 댓글 없음");
        }
        commentMapper.updateComment(comment);
    }

    @Override
    public void deleteComment(Long id) {
        if(commentMapper.findCommentById(id)==null){
            throw new CommentNotFoundException("해당 댓글 없음");
        }
        commentMapper.deleteComment(id);
    }

    @Override
    public List<Comment> findByPostId(Long id) {
        if(postMapper.findPostById(id)==null){
            throw new PostNotFoundException("해당 게시글 없음");
        }
        return commentMapper.findCommentByPostId(id);
    }

    @Override
    public List<Comment> findByUserId(Long id) {
        if(userMapper.findUserById(id)==null){
            throw new UserNotFoundException("해당 유저 없음");
        }

        return commentMapper.findCommentByUserId(id);
    }
}
