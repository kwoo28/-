package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@NoArgsConstructor
public class Message {
    @ApiModelProperty(example = "쪽지 고유 번호")
    private Long id;
    @ApiModelProperty(example = "쪽지 보낸 유저 고유 번호")
    private Long sendId;
    @ApiModelProperty(example = "쪽지 받는 유저 고유 번호")
    private Long recvId;
    @ApiModelProperty(example = "쪽지 보낸 일자")
    private Timestamp sendAt;
    @ApiModelProperty(example = "쪽지 읽은 일자")
    private Timestamp recvAt;
    @ApiModelProperty(example = "쪽지 확인 여부(0=읽지않음, 1=읽음)")
    private int checked;
    @ApiModelProperty(example = "쪽지 제목")
    private String title;
    @ApiModelProperty(example = "쪽지 내용")
    private String content;

    @Builder
    public Message(Long id, Long sendId, Long recvId, Timestamp sendAt, Timestamp recvAt, int checked, String title, String content) {
        this.id = id;
        this.sendId = sendId;
        this.recvId = recvId;
        this.sendAt = sendAt;
        this.recvAt = recvAt;
        this.checked = checked;
        this.title = title;
        this.content = content;
    }
}
