package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class Comment {
    @Schema(example = "댓글 고유 번호")
    private Long id;
    @Schema(example = "게시글 고유 번호")
    private Long postId;
    @Schema(example = "유저 고유 번호")
    private Long userId;
    @Schema(example = "댓글 내용")
    private String content;
    @Schema(example = "댓글 생성 일자")
    private Timestamp createdAt;

    @Builder
    public Comment(Long postId, Long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }
}