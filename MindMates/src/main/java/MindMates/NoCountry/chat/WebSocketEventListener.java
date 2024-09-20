package MindMates.NoCountry.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Project: Backend
 * Author: djorges
 * Date: 19/9/2024
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(
        SessionDisconnectEvent event
    ){
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) accessor.getSessionAttributes().get("username");
        if(username != null) {
            log.info("Disconnected from " + username);
            var chat = UserMessage.builder()
                    .username(username)
                    .type(MessageType.LEAVE)
                    .build();
            messagingTemplate.convertAndSend("/topic/reply", chat);
        }
    }
}
