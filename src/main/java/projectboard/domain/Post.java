package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import projectboard.dto.post.CreatePostReqDto;
import projectboard.dto.post.UpdatePostDto;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Post {
    @Schema(example = "게시글 고유 번호")
    private Long id;
    @Schema(example = "게시글 유저 번호")
    private Long userId;
    @Schema(example = "게시글 제목")
    private String title;
    @Schema(example = "게시글 내용")
    private String content;
    @Schema(example = "게시글 생성 일자")
    private Timestamp createdAt;

    public Post(CreatePostReqDto createPostReqDto) {
        this.userId = createPostReqDto.getUserId();
        this.title = createPostReqDto.getTitle();
        this.content = createPostReqDto.getContent();
    }

    public Post(UpdatePostDto updatePostDto){
        this.title = updatePostDto.getTitle();
        this.content = updatePostDto.getContent();
    }
}