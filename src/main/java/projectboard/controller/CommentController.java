package projectboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import projectboard.domain.Comment;
import projectboard.dto.comment.CreateCommentReqDto;
import projectboard.service.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "댓글 API", description = "댓글 API 입니다.")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "댓글생성", description = "json형태로 받은 데이터로 댓글 생성합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void createUser(@Valid @RequestBody CreateCommentReqDto createPostReqDto) {
        Comment comment = new Comment(createPostReqDto);
        commentService.createComment(comment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "댓글 수정", description = "댓글 고유 id와 json형태의 내용으로 수정합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void updateUser(@PathVariable("id") Long id, @RequestBody String content){
        Comment comment = Comment.builder().
                id(id).
                content(content).
                build();

        commentService.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "댓글 삭제", description = "댓글 고유 id로 받은 데이터로 삭제합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void deleteUser(@PathVariable("id") Long id){
        commentService.deleteComment(id);
    }

    @GetMapping("/postId/{postId}")
    @Operation(summary = "게시글 댓글 조회", description = "게시글 고유 id로 받은 데이터로 해당 게시글의 댓글들을 조회합니다.")
    public List<Comment> findByPostId(@PathVariable("postId") Long postId){
        return commentService.findByPostId(postId);
    }

    @GetMapping("/userId/{userId}")
    @Operation(summary = "유저 댓글 조회", description = "유저 고유 id로 받은 데이터로 유저가 쓴 댓글들을 조회합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public List<Comment> findByUserId(@PathVariable("userId") Long userId){
        return commentService.findByUserId(userId);
    }
}
