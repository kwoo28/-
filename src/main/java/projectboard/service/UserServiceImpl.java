package projectboard.service;

import lombok.RequiredArgsConstructor;
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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secret}")
    private String key;
    private Long expireTimsMs = 1000 * 60 * 60l;

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

        if(userMapper.findUserById(id)==null){
            throw new UserNotFoundException("해당 유저 없음");
        }

        User user = User.builder().
                id(id).
                userPw(updateUserReqDto.getUserPw()).
                userName(updateUserReqDto.getUserName()).
                email(updateUserReqDto.getEmail()).
                build();

        userMapper.updateUser(user);
    }

    @Override
    public User findById(Long id) {
        User findUser = userMapper.findUserById(id);
        if(findUser==null){
            throw new UserNotFoundException("회원 id 조회 실패");
        }
        return findUser;
    }

    @Override
    public User findByUserId(String userId) {
        User findUser = userMapper.findUserByUserId(userId);
        if(findUser==null){
            throw new UserNotFoundException("회원 아이디 조회 실패");
        }
        return findUser;
    }

    @Override
    public User findByUserName(String userName) {
        User findUser = userMapper.findUserByUserName(userName);
        if(findUser==null){
            throw new UserNotFoundException("회원 닉네임 조회 실패");
        }
        return findUser;
    }

    @Override
    public User findByEmail(String email) {
        User findUser = userMapper.findUserByEmail(email);
        if(findUser==null){
            throw new UserNotFoundException("회원 이메일 조회 실패");
        }
        return findUser;
    }

    @Override
    public List<User> findAllUser() {
        List<User> findUser = userMapper.findAllUser();
        if(findUser==null){
            throw new UserNotFoundException("모든 회원 조회 실패");
        }
        return findUser;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public String login(String userId, String userPw) {

        User findUser = userMapper.findUserByUserId(userId);

        if((findUser==null) || !encoder.matches(userPw, findUser.getUserPw())){
            throw new UserNotFoundException("로그인 실패했습니다.");
        }

        String token = JwtUtil.createJwt(userId, key, expireTimsMs);

        return token;
    }
}
