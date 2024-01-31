package projectboard.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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
    public void createMessage(Authentication authentication, @Valid @RequestBody CreateMessageReqDto createMessageReqDto){

        Long sendId = Long.parseLong(authentication.getName());

        messageService.createMessage(sendId, createMessageReqDto);
    }

    @GetMapping("/send-message")
    @Operation(summary = "보낸편지함", security = { @SecurityRequirement(name = "bearer-jwt") })
    public List<Message> findMessageBySendId(Authentication authentication){

        Long sendId = Long.parseLong(authentication.getName());
        return messageService.findMessageBySendId(sendId);
    }

    @GetMapping("/recv-message")
    @Operation(summary = "받은편지함", security = { @SecurityRequirement(name = "bearer-jwt") })
    public List<Message> findMessageByRecvId(Authentication authentication){

        Long recvId = Long.parseLong(authentication.getName());
        return messageService.findMessageByRecvId(recvId);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "쪽지 읽음 처리", security = { @SecurityRequirement(name = "bearer-jwt") })
    public void checkMessageById(@PathVariable("id") Long id){
        messageService.readMessage(id);
    }
}