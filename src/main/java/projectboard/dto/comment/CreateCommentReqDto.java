package projectboard.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentReqDto {
    @NotNull(message = "게시글 고유 id를 입력해주세요.")
    private Long postId;
    @NotNull(message = "유저 고유 id를 입력해주세요.")
    private Long userId;
    @NotNull(message = "댓글을 입력해주세요.")
    private String content;
}
