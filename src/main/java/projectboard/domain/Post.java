package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
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
}