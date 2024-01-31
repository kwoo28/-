package projectboard.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateCommentReqDto {
    @NotNull(message = "댓글을 입력해주세요.")
    private String content;
}
