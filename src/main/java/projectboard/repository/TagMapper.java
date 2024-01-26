package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.Tag;

@Mapper
public interface TagMapper {
    int createTag(@Param("name") String name);
    int deleteTag(@Param("id") Long id);
    Tag findTagByName(@Param("name") String name);
}
