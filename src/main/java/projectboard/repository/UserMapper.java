package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.User;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    int createUser(@Param("user") User user);

    int updateUser(@Param("user") User user);
    int deleteUser(@Param("id") Long id);
    Optional<User> findUserById(@Param("id") Long id);
    Optional<User> findUserByUserId(@Param("userId") String userId);
    Optional<User> findUserByUserName(@Param("userName") String userName);
    Optional<User> findUserByEmail(@Param("email") String email);
    List<User> findAllUser();
}
