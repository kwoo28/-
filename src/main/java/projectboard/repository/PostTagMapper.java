package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.PostTag;

@Mapper
public interface PostTagMapper {
    int createPostTag(@Param("postTag") PostTag postTag);
}
