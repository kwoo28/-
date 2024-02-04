package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.Tag;

import java.util.Optional;

@Mapper
public interface TagMapper {
    int createTag(@Param("name") String name);
    int deleteTag(@Param("id") Long id);
    Optional<Tag> findTagByName(@Param("name") String name);
}
