package projectboard.dto.user;

import lombok.Getter;
import lombok.Setter;
import projectboard.code.ResCode;

@Getter @Setter
public class DeleteUserResDto {
    private int code = ResCode.SUCCESS.value();
    private String message = "success";
}