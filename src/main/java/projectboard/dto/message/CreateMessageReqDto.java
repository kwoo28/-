package projectboard.dto.message;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMessageReqDto {
    @NotNull(message = "받는 고유 회원 id를 입력하세요.")
    private Long recvId;
    @NotNull(message = "제목을 입력하세요.")
    private String title;
    @NotNull(message = "내용을 입력하세요.")
    private String content;
}
