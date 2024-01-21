package projectboard.dto.user;

import lombok.Getter;
import lombok.Setter;
import projectboard.code.ResCode;
import projectboard.domain.User;

@Getter @Setter
public class FindUserResDto {
    private User user;
    private int code = ResCode.SUCCESS.value();
    private String message = "success";
}
