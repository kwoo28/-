package projectboard.repository;

import org.springframework.stereotype.Repository;
import projectboard.domain.User;

import java.util.List;

@Repository
public interface UserMapper {
    int createUser(User user);
    int updateUser(User user);
    void deleteUser(Long id);
    User findUserById(Long id);
    User findUserByUserId(String userId);
    User findUserByUserName(String userName);
    User findUserByEmail(String email);
    List<User> findAllUser();
}
