package projectboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import projectboard.domain.Message;
import projectboard.exception.NoSuchDataException;
import projectboard.repository.MessageMapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageMapper messageMapper;
    @Override
    public void createMessage(Message message) {
        Date date = new Date();
        long time = date.getTime();
        message.setSendAt(new Timestamp(time));
        messageMapper.createMessage(message);
    }

    @Override
    public void readMessage(Long id) {
        Message message = messageMapper.findMessageById(id);

        if(message==null) throw new NoSuchDataException("No such data exist");
        if(message.getChecked()==1) throw new DuplicateKeyException("already check message");
        if(message.getChecked()==0) messageMapper.readMessage(id);
    }

    @Override
    public Message findMessageById(Long id) {
        Message message = messageMapper.findMessageById(id);
        if(message==null){
            throw new NoSuchDataException("No such data exist");
        }
        return message;
    }

    @Override
    public List<Message> findMessageBySendId(Long sendId) {
        List<Message> message = messageMapper.findMessageBySendId(sendId);
        if(message.isEmpty()){
            throw new NoSuchDataException("No such data exist");
        }
        return message;
    }

    @Override
    public List<Message> findMessageByRecvId(Long recvId) {
        List<Message> message = messageMapper.findMessageByRecvId(recvId);
        if(message.isEmpty()){
            throw new NoSuchDataException("No such data exist");
        }
        return message;
    }
}
