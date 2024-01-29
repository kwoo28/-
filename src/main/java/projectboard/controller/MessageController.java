package projectboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import projectboard.domain.Message;
import projectboard.dto.message.*;
import projectboard.service.MessageService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
@Tag(name = "쪽지 API", description = "간단한 쪽지 기능 API")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "쪽지생성", security = { @SecurityRequirement(name = "bearer-jwt") })
    public void createMessage(@Valid @RequestBody CreateMessageReqDto createMessageReqDto){
        Message message = new Message(createMessageReqDto);
        messageService.createMessage(message);
    }

    @GetMapping("/{id}")
    @Operation(summary = "편지 조회", security = { @SecurityRequirement(name = "bearer-jwt") })
    public Message findMessageById(@PathVariable("id") Long id){
        return messageService.findMessageById(id);
    }

    @GetMapping("/send-message/{sendId}")
    @Operation(summary = "보낸편지함", security = { @SecurityRequirement(name = "bearer-jwt") })
    public List<Message> findMessageBySendId(@PathVariable("sendId") Long sendId){
        return messageService.findMessageBySendId(sendId);
    }

    @GetMapping("/recv-message/{recvId}")
    @Operation(summary = "받은편지함", security = { @SecurityRequirement(name = "bearer-jwt") })
    public List<Message> findMessageByRecvId(@PathVariable("recvId") Long recvId){
        return messageService.findMessageByRecvId(recvId);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "쪽지 읽음 처리", security = { @SecurityRequirement(name = "bearer-jwt") })
    public void checkMessageById(@PathVariable("id") Long id){
        messageService.readMessage(id);
    }
}