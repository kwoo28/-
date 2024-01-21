package projectboard.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
// 널 값의 속성은 포함하지 않음
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginReqDto {
    private String userId;
    private String userPw;
}