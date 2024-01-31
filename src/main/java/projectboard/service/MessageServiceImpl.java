package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import projectboard.domain.Message;
import projectboard.dto.message.CreateMessageReqDto;
import projectboard.exception.MessageNotFoundException;
import projectboard.repository.MessageMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageMapper messageMapper;
    @Override
    public void createMessage(Long sendId, CreateMessageReqDto createMessageReqDto) {

        Message message = Message.builder().
                sendId(sendId).
                recvId(createMessageReqDto.getRecvId()).
                title(createMessageReqDto.getTitle()).
                content(createMessageReqDto.getContent()).
                build();

        messageMapper.createMessage(message);
    }

    @Override
    public void readMessage(Long id) {
        Message message = messageMapper.findMessageById(id);

        if(message==null) throw new MessageNotFoundException("쪽지를 찾을 수 없습니다.");
        if(message.getChecked()==1) throw new DuplicateKeyException("이미 읽음 확인했습니다.");
        if(message.getChecked()==0) messageMapper.readMessage(id);
    }

    @Override
    public Message findMessageById(Long id) {
        Message message = messageMapper.findMessageById(id);
        if(message==null){
            throw new MessageNotFoundException("쪽지 id 조회 실패");
        }
        return message;
    }

    @Override
    public List<Message> findMessageBySendId(Long sendId) {
        List<Message> message = messageMapper.findMessageBySendId(sendId);
        if(message.isEmpty()){
            throw new MessageNotFoundException("쪽지 보낸이 id 조회 실패");
        }
        return message;
    }

    @Override
    public List<Message> findMessageByRecvId(Long recvId) {
        List<Message> message = messageMapper.findMessageByRecvId(recvId);
        if(message.isEmpty()){
            throw new MessageNotFoundException("쪽지 받는이 id 조회 실패");
        }
        return message;
    }
}