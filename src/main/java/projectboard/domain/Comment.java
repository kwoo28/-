package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class Comment {
    @ApiModelProperty(example = "댓글 고유 번호")
    private Long id;
    @ApiModelProperty(example = "게시글 고유 번호")
    private Long postId;
    @ApiModelProperty(example = "유저 고유 번호")
    private Long userId;
    @ApiModelProperty(example = "댓글 내용")
    private String content;
    @ApiModelProperty(example = "댓글 생성 일자")
    private LocalDateTime createdAt;

    @Builder
    public Comment(Long postId, Long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }
}