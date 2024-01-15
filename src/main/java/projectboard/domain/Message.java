package projectboard.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class Message {
    private Long id;
    private String sendUserName;
    private String receiveUserName;
    private LocalDateTime sendAt;
    private LocalDateTime receiveAt;
    private int checked;
    private String title;
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
