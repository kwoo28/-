package projectboard.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import projectboard.dto.user.CreateUserReqDto;
import projectboard.dto.user.UpdateUserReqDto;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@AllArgsConstructor
public class User {
    @Schema(example = "유저 고유 번호")
    private Long id;
    @Schema(example = "유저 아이디")
    private String userId;
    @Schema(example = "유저 비밀번호")
    private String userPw;
    @Schema(example = "유저 닉네임")
    private String userName;
    @Schema(example = "유저 이메일")
    private String email;
    @Schema(example = "유저 생성 일자")
    private Timestamp createdAt;

    public User(UpdateUserReqDto updateUserReqDto) {
        this.userPw = updateUserReqDto.getUserPw();
        this.userName = updateUserReqDto.getUserName();
        this.email = updateUserReqDto.getEmail();
    }

    public User(CreateUserReqDto createUserReqDto) {
        Date date = new Date();
        long time = date.getTime();

        this.userId = createUserReqDto.getUserId();
        this.userPw = createUserReqDto.getUserPw();
        this.userName = createUserReqDto.getUserName();
        this.email = createUserReqDto.getEmail();
        this.createdAt = new Timestamp(time);
    }
}
