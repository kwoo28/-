package projectboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import projectboard.domain.User;
import projectboard.dto.user.*;
import projectboard.service.KakaoService;
import projectboard.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "회원 API", description = "기본적인 회원 CRUD API")
public class UserController {

    private final UserService userService;
    private final KakaoService kakaoService;

    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "json형태로 받은 데이터로 회원가입합니다.")
    public void createUser(@Valid @RequestBody CreateUserReqDto createUserReqDto){
        userService.createUser(createUserReqDto);
    }

    @PutMapping
    @Operation(summary = "회원수정", description = "회원 고유 id와 json형태로 받은 데이터로 수정합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void updateUser(Authentication authentication, @RequestBody UpdateUserReqDto updateUserReqDto){
        Long userId = Long.parseLong(authentication.getName());
        userService.updateUser(userId, updateUserReqDto);
    }

    @GetMapping("/userId/{userId}")
    @Operation(summary = "회원 아이디 조회", description = "회원 아이디로 받은 데이터로 조회합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public User findByUserId(@PathVariable("userId") String userId){
        return userService.findByUserId(userId);
    }

    @GetMapping("/userName/{userName}")
    @Operation(summary = "회원 이름 조회", description = "회원 이름으로 받은 데이터로 조회합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public User findByUserName(@PathVariable("userName") String userName){
        return userService.findByUserName(userName);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "회원 이메일 조회", description = "회원 이메일로 받은 데이터로 조회합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public User findByEmail(@PathVariable("email") String email){
        return userService.findByEmail(email);
    }

    @DeleteMapping
    @Operation(summary = "회원 삭제", description = "회원 고유 id로 받은 데이터로 삭제합니다.",
            security = { @SecurityRequirement(name = "bearer-jwt") })
    public void deleteUser(Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userService.deleteUser(userId);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "회원아이디와 비밀번호로 로그인합니다.")
    public String login(@Valid @RequestBody LoginReqDto loginReqDto){
        return userService.login(loginReqDto.getUserId(), loginReqDto.getUserPw());
    }

    @GetMapping("/kakao/callback")
    @Operation(summary = "카카오 로그인", description = "만약 카카오유저 db에 존재하면 로그인, 존재안하면 회원가입 시킴.")
    public String kakaoLogin(@RequestParam String code){
        return kakaoService.kakaoLogin(code);
    }
}