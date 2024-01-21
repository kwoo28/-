package projectboard.dto.message;

import lombok.Getter;

@Getter
public class CreateMessageReqDto {
    private Long sendId;
    private Long recvId;
    private String title;
    private String content;
}
