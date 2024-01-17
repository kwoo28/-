package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class RecvMessage {
    @ApiModelProperty(example = "쪽지 고유 번호")
    private Long id;
    @ApiModelProperty(example = "쪽지 보낸 유저 고유 번호")
    private Long sendUserId;
    @ApiModelProperty(example = "쪽지 받는 유저 고유 번호")
    private Long recvUserId;
    @ApiModelProperty(example = "쪽지 보낸 일자")
    private Timestamp sendAt;
    @ApiModelProperty(example = "쪽지 읽은 일자")
    private Timestamp receiveAt;
    @ApiModelProperty(example = "쪽지 제목")
    private String title;
    @ApiModelProperty(example = "쪽지 내용")
    private String content;

    @Builder
    public RecvMessage(Long sendUserId, Long recvUserId, String title, String content) {
        this.sendUserId = sendUserId;
        this.recvUserId = recvUserId;
        this.title = title;
        this.content = content;
    }
}
