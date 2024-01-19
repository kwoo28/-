package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import projectboard.domain.User;
import projectboard.repository.UserMapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    //유저 아이디 중복 확인
    private void checkDuplicatedUserId(String userId){
        if(userId!=null){
            if(userMapper.findUserByUserId(userId) != null){
                throw new IllegalArgumentException();
            }
        }
    }

    //유저 닉네임 중복 확인
    private void checkDuplicatedUserName(String userName){
        if(userName!=null){
            if(userMapper.findUserByUserName(userName) != null){
                throw new IllegalArgumentException();
            }
        }
    }

    //유저 이메일 중복 확인
    private void checkDuplicatedEmail(String email){
        if(email!=null){
            if(userMapper.findUserByEmail(email) != null){
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public User createUser(User user) {

        checkDuplicatedUserId(user.getUserId());
        checkDuplicatedUserName(user.getUserName());
        checkDuplicatedEmail(user.getEmail());

        Date date = new Date();
        long time = date.getTime();
        user.setCreatedAt(new Timestamp(time));
        userMapper.createUser(user);

        return user;
    }

    @Override
    public User findByUserId(String userId) {

        if(userMapper.findUserByUserId(userId)==null){
            throw new IllegalArgumentException("회원 식별 id를 찾을 수 없습니다.");
        }

        return userMapper.findUserByUserId(userId);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User updateUser(Long id, User user) throws NotFoundException {
        if(userMapper.findUserById(id)==null){
            throw new NotFoundException("");
        }

        //유저아이디는 고정값이기때문에 수정할 필요가 없음.
        checkDuplicatedUserName(user.getUserName());
        checkDuplicatedEmail(user.getEmail());

        user.setCreatedAt((Timestamp) new Date());
        userMapper.updateUser(user);

        return userMapper.findUserById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }

    @Override
    public boolean login(String userId, String userPw) {
        if(userMapper.findUserByUserId(userId)==null){
            return false;
        } else{
            User findUser = userMapper.findUserByUserId(userId);
            return (findUser.getUserPw() == userPw);
        }
    }
}
