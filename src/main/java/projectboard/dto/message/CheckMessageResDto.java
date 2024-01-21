package projectboard.dto.message;

import lombok.Getter;
import lombok.Setter;
import projectboard.code.ResCode;

@Getter @Setter
public class CheckMessageResDto {
    private int code = ResCode.SUCCESS.value();
    private String message = "success";
}
