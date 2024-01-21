package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import projectboard.domain.User;
import projectboard.exception.NoSuchDataException;
import projectboard.repository.UserMapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Override
    public void createUser(User user) {
        Date date = new Date();
        long time = date.getTime();
        user.setCreatedAt(new Timestamp(time));
        userMapper.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        int result = userMapper.updateUser(user);
        if(result == 0){
            throw new NoSuchDataException("No such data exist");
        }
    }

    @Override
    public User findById(Long id) {
        User findUser = userMapper.findUserById(id);
        if(findUser==null){
            throw new NoSuchDataException("No such data exist");
        }
        return findUser;
    }

    @Override
    public User findByUserId(String userId) {
        User findUser = userMapper.findUserByUserId(userId);
        if(findUser==null){
            throw new NoSuchDataException("No such data exist");
        }
        return findUser;
    }

    @Override
    public User findByUserName(String userName) {
        User findUser = userMapper.findUserByUserName(userName);
        if(findUser==null){
            throw new NoSuchDataException("No such data exist");
        }
        return findUser;
    }

    @Override
    public User findByEmail(String email) {
        User findUser = userMapper.findUserByEmail(email);
        if(findUser==null){
            throw new NoSuchDataException("No such data exist");
        }
        return findUser;
    }

    @Override
    public List<User> findAllUser() {
        List<User> findUser = userMapper.findAllUser();
        if(findUser==null){
            throw new NoSuchDataException("No such data exists");
        }
        return findUser;
    }

    @Override
    public void deleteUser(Long id) {
        int result = userMapper.deleteUser(id);
        if(result == 0){
            throw new NoSuchDataException("No such data exist");
        }
    }

    @Override
    public void login(String userId, String userPw) {

        User findUser = userMapper.findUserByUserId(userId);

        if((findUser==null) || !(findUser.getUserPw().equals(userPw))){
            throw new NoSuchDataException("No such data exist");
        }
    }
}
