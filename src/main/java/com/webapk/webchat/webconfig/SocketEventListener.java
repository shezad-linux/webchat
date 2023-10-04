package com.webapk.webchat.webconfig;

import com.webapk.webchat.model.ChatMessage;
import com.webapk.webchat.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketEventListener {
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
    {
        StompHeaderAccessor headerAccessor =StompHeaderAccessor.wrap(event.getMessage());
        String username  = (String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null){
            log.info("user disconnected:{}", username);
            var chatMessage = ChatMessage.builder()
                    .type(Message.LEAVE)
                    .sender(username)
                    .build();
            messageTemplate.convertAndSend("/topic/public", chatMessage);



        }

    }




}
