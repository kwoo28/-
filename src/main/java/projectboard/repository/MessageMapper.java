package projectboard.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import projectboard.domain.Message;

import java.util.List;

@Mapper
public interface MessageMapper {
    int createMessage(@Param("message") Message message);
    int deleteMessage(@Param("id") Long id);
    Message findMessageById(@Param("id") Long id);
    List<Message> findMessageBySendId(@Param("sendId") Long sendId);
    List<Message> findMessageByRecvId(@Param("recvId") Long recvId);
    int readMessage(@Param("id") Long id);
}