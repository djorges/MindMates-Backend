package MindMates.NoCountry.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController{

    @MessageMapping("/broadcast")
    @SendTo("/topic/reply")
    public UserMessage broadcastMessage(@Payload UserMessage message) {
        return message;
    }

    @MessageMapping("/add")
    @SendTo("/topic/reply")
    public UserMessage addUser(@Payload UserMessage message, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", message.getUsername());
        return message;
    }
}
