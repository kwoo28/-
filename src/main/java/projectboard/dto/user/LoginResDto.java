package projectboard.dto.user;

import lombok.Getter;
import lombok.Setter;
import projectboard.code.ResCode;

@Getter @Setter
public class LoginResDto {
    private int code = ResCode.SUCCESS.value();
    private String message = "success";
}