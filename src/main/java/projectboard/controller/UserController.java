package projectboard.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectboard.code.ResCode;
import projectboard.domain.User;
import projectboard.dto.user.*;
import projectboard.exception.NoSuchDataException;
import projectboard.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "회원가입")
    public ResponseEntity<CreateUserResDto> createUser(@RequestBody CreateUserReqDto createUserReqDto){

        CreateUserResDto createUserResDto = new CreateUserResDto();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {
            User user = User.builder().
                    userId(createUserReqDto.getUserId()).
                    userPw(createUserReqDto.getUserPw()).
                    userName(createUserReqDto.getUserName()).
                    email(createUserReqDto.getEmail()).
                    build();

            userService.createUser(user);

            //DuplicateKeyException를 먼저 한 이유는 구체적인 중복예외를 처리하기 위해(DataIntegrityViolationException에 DuplicateKeyException이 포함됨)
        }catch (DuplicateKeyException e){ //중복 키(Primary Key 또는 Unique Key) 제약 조건 위배로 인한 예외
            createUserResDto.setCode(ResCode.DUPLICATE_KEY.value());
            createUserResDto.setMessage("'userId' or 'username' or 'email' 가 중복되었습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (DataIntegrityViolationException e){ //데이터 무결성 위배와 관련된 예외
            createUserResDto.setCode(ResCode.NULL_VALUE.value());
            createUserResDto.setMessage("회원 정보를 입력해주세요");
            httpStatus = HttpStatus.BAD_REQUEST;
        }catch (Exception e) {
            log.error("[UserController createUser]", e);
            createUserResDto.setCode(ResCode.UNKNOWN.value());
            createUserResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(createUserResDto, httpStatus);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "회원 수정")
    public ResponseEntity<UpdateUserResDto> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserReqDto updateUserReqDto){

        UpdateUserResDto updateUserResDto = new UpdateUserResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            User user = new User();
            user.setId(id);
            user.setUserPw(updateUserReqDto.getUserPw());
            user.setUserName(updateUserReqDto.getUserName());
            user.setEmail(updateUserReqDto.getEmail());

            userService.updateUser(user);

        } catch (NoSuchDataException e){
            updateUserResDto.setCode(ResCode.NO_SUCH_DATA.value());
            updateUserResDto.setMessage("회원 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (DuplicateKeyException e){
            updateUserResDto.setCode(ResCode.DUPLICATE_KEY.value());
            updateUserResDto.setMessage("'username' or 'email' 가 중복되었습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (DataIntegrityViolationException e){
            updateUserResDto.setCode(ResCode.NULL_VALUE.value());
            updateUserResDto.setMessage("회원 정보를 입력해주세요");
            httpStatus = HttpStatus.BAD_REQUEST;
        }catch (Exception e){
            log.error("UserController updateUser", e);
            updateUserResDto.setCode(ResCode.UNKNOWN.value());
            updateUserResDto.setMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(updateUserResDto, httpStatus);
    }

    @GetMapping("/userId/{userId}")
    @ApiOperation(value = "회원아이디 조회")
    public ResponseEntity<FindUserResDto> findByUserId(@PathVariable("userId") String userId){

        FindUserResDto findUserResDto = new FindUserResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            findUserResDto.setUser(userService.findByUserId(userId));
        } catch (NoSuchDataException e) {
            findUserResDto.setCode(ResCode.NO_SUCH_DATA.value());
            findUserResDto.setMessage("회원 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("UserController findByUserId", e);
            findUserResDto.setCode(ResCode.UNKNOWN.value());
            findUserResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(findUserResDto, httpStatus);
    }

    @GetMapping("/userName/{userName}")
    @ApiOperation(value = "회원이름 조회")
    public ResponseEntity<FindUserResDto> findByUserName(@PathVariable("userName") String userName){

        FindUserResDto findUserResDto = new FindUserResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            findUserResDto.setUser(userService.findByUserName(userName));
        } catch (NoSuchDataException e) {
            findUserResDto.setCode(ResCode.NO_SUCH_DATA.value());
            findUserResDto.setMessage("회원 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("UserController findByUserName", e);
            findUserResDto.setCode(ResCode.UNKNOWN.value());
            findUserResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(findUserResDto, httpStatus);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "회원 이메일 조회")
    public ResponseEntity<FindUserResDto> findByEmail(@PathVariable("email") String email){
        FindUserResDto findUserResDto = new FindUserResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            findUserResDto.setUser(userService.findByEmail(email));
        } catch (NoSuchDataException e) {
            findUserResDto.setCode(ResCode.NO_SUCH_DATA.value());
            findUserResDto.setMessage("회원 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("UserController findByEmail", e);
            findUserResDto.setCode(ResCode.UNKNOWN.value());
            findUserResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(findUserResDto, httpStatus);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<DeleteUserResDto> deleteUser(@PathVariable("id") Long id){
        DeleteUserResDto deleteUserRespDto = new DeleteUserResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            userService.deleteUser(id);
        } catch (NoSuchDataException e) {
            deleteUserRespDto.setCode(ResCode.NO_SUCH_DATA.value());
            deleteUserRespDto.setMessage("회원 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("[UserController deleteUser]", e);
            deleteUserRespDto.setCode(ResCode.UNKNOWN.value());
            deleteUserRespDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(deleteUserRespDto, httpStatus);
    }

    @PostMapping("/login")
    @ApiOperation(value = "회원 로그인")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto){

        LoginResDto loginResDto = new LoginResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            userService.login(loginReqDto.getUserId(), loginReqDto.getUserPw());
        } catch (NoSuchDataException e){
            loginResDto.setCode(ResCode.NO_SUCH_DATA.value());
            loginResDto.setMessage("회원 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e){
            log.error("UserController login", e);
            loginResDto.setCode(ResCode.UNKNOWN.value());
            loginResDto.setMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(loginResDto, httpStatus);
    }
}
