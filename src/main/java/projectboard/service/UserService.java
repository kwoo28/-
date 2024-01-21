package projectboard.service;

import org.apache.ibatis.javassist.NotFoundException;
import projectboard.domain.User;
import projectboard.dto.user.UpdateUserReqDto;

import java.util.List;

public interface UserService {
    void createUser(User user);
    User findById(Long id);
    User findByUserId(String userId);
    User findByUserName(String userName);
    User findByEmail(String email);
    List<User> findAllUser();
    void updateUser(User user);
    void deleteUser(Long id);
    void login(String userId, String userPw);
}
