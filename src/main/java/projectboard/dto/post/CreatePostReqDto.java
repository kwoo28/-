package projectboard.dto.post;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class CreatePostReqDto {
    @NotNull(message = "게시글 제목을 입력해주세요.")
    private String title;
    @NotNull(message = "게시글 내용을 입력해주세요.")
    private String content;
    private List<String> tagNames;
}
