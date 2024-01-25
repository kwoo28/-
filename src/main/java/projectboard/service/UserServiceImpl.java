package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectboard.domain.User;
import projectboard.exception.UserNotFoundException;
import projectboard.repository.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Override
    public void createUser(User user) {
        userMapper.createUser(user);
    }

    @Override
    public void updateUser(User user) {
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
    public void login(String userId, String userPw) {

        User findUser = userMapper.findUserByUserId(userId);

        if((findUser==null) || !(findUser.getUserPw().equals(userPw))){
            throw new UserNotFoundException("로그인 실패했습니다.");
        }
    }
}
