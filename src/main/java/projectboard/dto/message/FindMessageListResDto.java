package projectboard.dto.message;

import lombok.Getter;
import lombok.Setter;
import projectboard.code.ResCode;
import projectboard.domain.Message;

import java.util.List;

@Getter @Setter
public class FindMessageListResDto {
    private List<Message> findMessage;
    private int code = ResCode.SUCCESS.value();
    private String message = "success";
}
