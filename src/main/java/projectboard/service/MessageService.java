package projectboard.service;

import projectboard.domain.Message;

import java.util.List;

public interface MessageService {
    void createMessage(Message message);
    void readMessage(Long id);
    Message findMessageById(Long id);
    List<Message> findMessageBySendId(Long sendId);
    List<Message> findMessageByRecvId(Long recvId);
}