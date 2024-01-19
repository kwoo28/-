package projectboard.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectboard.domain.User;
import projectboard.dto.user.LoginRequest;
import projectboard.dto.user.RegisterRequest;
import projectboard.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "회원가입")
    public ResponseEntity<User> createUser(@RequestBody RegisterRequest register){
        User user = new User();
        user.setUserId(register.getUserId());
        user.setUserPw(register.getUserPw());
        user.setUserName(register.getUserName());
        user.setEmail(register.getEmail());

        User createUser = userService.createUser(user);
        return new ResponseEntity(createUser, HttpStatus.CREATED);
    }

    @GetMapping("/userId/{userId}")
    @ApiOperation(value = "회원아이디 조회")
    public ResponseEntity<User> findByUserId(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/userName/{userName}")
    @ApiOperation(value = "회원이름 조회")
    public ResponseEntity<User> findByUserName(@PathVariable("userName") String userName){
        return new ResponseEntity<>(userService.findByUserName(userName), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "회원 이메일 조회")
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value = "회원 로그인")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        if(userService.login(loginRequest.getUserId(), loginRequest.getUserPw()))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "회원 수정")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody RegisterRequest findUser) throws NotFoundException {
        User user = new User();
        user.setUserId(findUser.getUserId());
        user.setUserPw(findUser.getUserPw());
        user.setUserName(findUser.getUserName());
        user.setEmail(findUser.getEmail());

        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
