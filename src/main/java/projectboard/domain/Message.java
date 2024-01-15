package projectboard.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @ApiModelProperty(example = "쪽지 고유 번호")
    private Long id;
    @ApiModelProperty(example = "쪽지 보낸이")
    private String sendUserName;
    @ApiModelProperty(example = "쪽지 받는이")
    private String receiveUserName;
    @ApiModelProperty(example = "쪽지 보낸 일자")
    private LocalDateTime sendAt;
    @ApiModelProperty(example = "쪽지 읽은 일자")
    private LocalDateTime receiveAt;
    @ApiModelProperty(example = "쪽지 확인 여부(0=읽지않음, 1=읽음)")
    private int checked;
    @ApiModelProperty(example = "쪽지 제목")
    private String title;
    @ApiModelProperty(example = "쪽지 내용")
    private String content;

    public Message(String sendUserName, String receiveUserName, LocalDateTime sendAt, LocalDateTime receiveAt, int checked, String title, String content) {
        this.sendUserName = sendUserName;
        this.receiveUserName = receiveUserName;
        this.sendAt = sendAt;
        this.receiveAt = receiveAt;
        this.checked = checked;
        this.title = title;
        this.content = content;
    }
}
