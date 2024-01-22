package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class Post {
    @Schema(example = "게시글 고유 번호")
    private Long id;
    @Schema(example = "게시글 유저 번호")
    private Long userId;
    @Schema(example = "게시글 제목")
    private String title;
    @Schema(example = "게시글 내용")
    private String content;
    @Schema(example = "게시글 해시 태그")
    private String hashTag;
    @Schema(example = "게시글 생성 일자")
    private Timestamp createdAt;


    @Builder
    public Post(Long userId, String title, String content, String hashTag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }
}