package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    int createUser(@Param("user") User user);

    int updateUser(@Param("user") User user);
    int deleteUser(@Param("id") Long id);
    User findUserById(@Param("id") Long id);
    User findUserByUserId(@Param("userId") String userId);
    User findUserByUserName(@Param("userName") String userName);
    User findUserByEmail(@Param("email") String email);
    List<User> findAllUser();
}
