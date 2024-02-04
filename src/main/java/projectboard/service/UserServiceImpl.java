package projectboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projectboard.domain.User;
import projectboard.dto.user.CreateUserReqDto;
import projectboard.dto.user.UpdateUserReqDto;
import projectboard.exception.UserNotFoundException;
import projectboard.repository.UserMapper;
import projectboard.util.JwtUtil;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String key;
    private Long expireTimeMs = 1000 * 60 * 60l;

    @Override
    public void createUser(CreateUserReqDto createUserReqDto) {

        User user = User.builder().
                userId(createUserReqDto.getUserId()).
                userPw(encoder.encode(createUserReqDto.getUserPw())).
                userName(createUserReqDto.getUserName()).
                email(createUserReqDto.getEmail()).
                build();

        userMapper.createUser(user);
    }

    @Override
    public void updateUser(Long id, UpdateUserReqDto updateUserReqDto) {

        userMapper.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("회원 id 조회 실패"));

        User user = User.builder().
                id(id).
                userPw(encoder.encode(updateUserReqDto.getUserPw())).
                userName(updateUserReqDto.getUserName()).
                email(updateUserReqDto.getEmail()).
                build();

        userMapper.updateUser(user);
    }

    @Override
    public User findById(Long id) {
        User findUser = userMapper.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("회원 id 조회 실패"));
        return findUser;
    }

    @Override
    public User findByUserId(String userId) {
        User findUser = userMapper.findUserByUserId(userId).
                orElseThrow(() -> new UserNotFoundException("회원 아이디 조회 실패"));
        return findUser;
    }

    @Override
    public User findByUserName(String userName) {
        User findUser = userMapper.findUserByUserName(userName).
                orElseThrow(() -> new UserNotFoundException("회원 닉네임 조회 실패"));
        return findUser;
    }

    @Override
    public User findByEmail(String email) {
        User findUser = userMapper.findUserByEmail(email).
                orElseThrow(() -> new UserNotFoundException("회원 이메일 조회 실패"));
        return findUser;
    }

    @Override
    public List<User> findAllUser() {
        List<User> findUser = userMapper.findAllUser();

        if(findUser.isEmpty()){
            throw new UserNotFoundException("모든 회원 조회 실패");
        }

        return findUser;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("회원 id 조회 실패"));

        userMapper.deleteUser(id);
    }

    @Override
    public String login(String userId, String userPw) {

        User findUser = userMapper.findUserByUserId(userId).
                orElseThrow(() -> new UserNotFoundException("해당 아이디가 존재하지 않습니다."));

        if(!encoder.matches(userPw, findUser.getUserPw())){
            throw new UserNotFoundException("로그인 실패하였습니다.");
        }

        String token = JwtUtil.createJwt(findUser.getId(), key, expireTimeMs);
        log.info("{} 님이 로그인을 시도합니다.", findUser.getUserId());
        log.info("token을 발급합니다. : {}", token);

        return token;
    }
}
