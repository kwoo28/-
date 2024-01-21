package projectboard.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectboard.code.ResCode;
import projectboard.domain.Message;
import projectboard.dto.message.*;
import projectboard.exception.NoSuchDataException;
import projectboard.service.MessageService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ApiOperation("쪽지생성")
    public ResponseEntity<CreateMessageResDto> createMessage(@RequestBody CreateMessageReqDto createMessageReqDto){

        CreateMessageResDto createMessageResDto = new CreateMessageResDto();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {
            Message message = new Message();
            message.setSendId(createMessageReqDto.getSendId());
            message.setRecvId(createMessageReqDto.getRecvId());
            message.setTitle(createMessageReqDto.getTitle());
            message.setContent(createMessageReqDto.getContent());

            messageService.createMessage(message);

        } catch (DataIntegrityViolationException e){ //데이터 무결성 위배와 관련된 예외
            createMessageResDto.setCode(ResCode.NULL_VALUE.value());
            createMessageResDto.setMessage("쪽지 정보 오류");
            httpStatus = HttpStatus.BAD_REQUEST;
        }catch (Exception e) {
            log.error("[MessageController createMessage]", e);
            createMessageResDto.setCode(ResCode.UNKNOWN.value());
            createMessageResDto.setMessage(e.getMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(createMessageResDto, httpStatus);
    }

    @GetMapping("/{id}")
    @ApiOperation("편지 조회")
    public ResponseEntity<FindMessageResDto> findMessageById(@PathVariable("id") Long id){
        FindMessageResDto findMessageResDto = new FindMessageResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            findMessageResDto.setFindMessage(messageService.findMessageById(id));
        } catch (NoSuchDataException e) {
            findMessageResDto.setCode(ResCode.NO_SUCH_DATA.value());
            findMessageResDto.setMessage("쪽지 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("[MessageController findMessageById]", e);
            findMessageResDto.setCode(ResCode.UNKNOWN.value());
            findMessageResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(findMessageResDto, httpStatus);
    }

    @GetMapping("/send-message/{sendId}")
    @ApiOperation("보낸편지함")
    public ResponseEntity<FindMessageListResDto> findMessageBySendId(@PathVariable("sendId") Long sendId){

        FindMessageListResDto findMessageListResDto = new FindMessageListResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            findMessageListResDto.setFindMessage(messageService.findMessageBySendId(sendId));
        } catch (NoSuchDataException e) {
            findMessageListResDto.setCode(ResCode.NO_SUCH_DATA.value());
            findMessageListResDto.setMessage("쪽지 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("[MessageController findMessageBySendId]", e);
            findMessageListResDto.setCode(ResCode.UNKNOWN.value());
            findMessageListResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(findMessageListResDto, httpStatus);
    }

    @GetMapping("/recv-message/{recvId}")
    @ApiOperation("받은편지함")
    public ResponseEntity<FindMessageListResDto> findMessageByRecvId(@PathVariable("recvId") Long recvId){

        FindMessageListResDto findMessageListResDto = new FindMessageListResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            findMessageListResDto.setFindMessage(messageService.findMessageByRecvId(recvId));
        } catch (NoSuchDataException e) {
            findMessageListResDto.setCode(ResCode.NO_SUCH_DATA.value());
            findMessageListResDto.setMessage("쪽지 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("MessageController findMessageByRecvId", e);
            findMessageListResDto.setCode(ResCode.UNKNOWN.value());
            findMessageListResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(findMessageListResDto, httpStatus);
    }

    @PatchMapping("/{id}")
    @ApiOperation("쪽지 읽음 처리")
    public ResponseEntity<CheckMessageResDto> checkMessageById(@PathVariable("id") Long id){
        CheckMessageResDto checkMessageResDto = new CheckMessageResDto();
        HttpStatus httpStatus = HttpStatus.OK;

        try{
            messageService.readMessage(id);
        } catch (NoSuchDataException e){
            checkMessageResDto.setCode(ResCode.NO_SUCH_DATA.value());
            checkMessageResDto.setMessage("쪽지 정보를 찾을 수 없습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (DuplicateKeyException e){
            checkMessageResDto.setCode(ResCode.DUPLICATE_KEY.value());
            checkMessageResDto.setMessage("이미 읽음 표시가 되었습니다.");
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            log.error("MessageController checkMessageById", e);
            checkMessageResDto.setCode(ResCode.UNKNOWN.value());
            checkMessageResDto.setMessage(e.getLocalizedMessage());
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(checkMessageResDto, httpStatus);
    }
}
