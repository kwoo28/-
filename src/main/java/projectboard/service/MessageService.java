package projectboard.service;

import projectboard.domain.Message;
import projectboard.dto.message.CreateMessageReqDto;

import java.util.List;

public interface MessageService {
    void createMessage(Long sendId, CreateMessageReqDto createMessageReqDto);
    void readMessage(Long id);
    Message findMessageById(Long id);
    List<Message> findMessageBySendId(Long sendId);
    List<Message> findMessageByRecvId(Long recvId);
}