package projectboard.service;

import projectboard.domain.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User findByUserId(String userId);
    User findByUserName(String userName);
    User findByEmail(String email);
    List<User> findAllUser();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    boolean login(String userId, String userPw);
}
