package projectboard.service;

import org.apache.ibatis.javassist.NotFoundException;
import projectboard.domain.User;
import projectboard.dto.user.CreateUserReqDto;
import projectboard.dto.user.UpdateUserReqDto;

import java.util.List;

public interface UserService {
    void createUser(CreateUserReqDto createUserReqDto);
    User findById(Long id);
    User findByUserId(String userId);
    User findByUserName(String userName);
    User findByEmail(String email);
    List<User> findAllUser();
    void updateUser(Long id, UpdateUserReqDto updateUserReqDto);
    void deleteUser(Long id);
    String login(String userId, String userPw);
}
