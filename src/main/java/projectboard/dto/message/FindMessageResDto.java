package projectboard.dto.message;

import lombok.Getter;
import lombok.Setter;
import projectboard.code.ResCode;
import projectboard.domain.Message;

@Getter @Setter
public class FindMessageResDto {
    private Message findMessage;
    private int code = ResCode.SUCCESS.value();
    private String message = "success";
}
