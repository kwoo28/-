package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
public class Message {
    @Schema(example = "쪽지 고유 번호")
    private Long id;
    @Schema(example = "쪽지 보낸 유저 고유 번호")
    private Long sendId;
    @Schema(example = "쪽지 받는 유저 고유 번호")
    private Long recvId;
    @Schema(example = "쪽지 보낸 일자")
    private Timestamp sendAt;
    @Schema(example = "쪽지 읽은 일자")
    private Timestamp recvAt;
    @Schema(example = "쪽지 확인 여부(0=읽지않음, 1=읽음)")
    private int checked;
    @Schema(example = "쪽지 제목")
    private String title;
    @Schema(example = "쪽지 내용")
    private String content;
}
