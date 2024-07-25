package com.fin.oopsproject.Controllers;

import com.fin.oopsproject.Model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping(path = "/")
    public Iterable<MessageModel> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping(path = "/user")
    public Iterable<MessageModel> getAllMessagesForUser(@RequestParam Long userId) {
        return messageService.getMessagesForUser(userId);
    }

    @GetMapping(path = "/product")
    public Iterable<MessageModel> getMessagesByProductId(@RequestParam Long productId) {
        return messageService.getMessagesByProductId(productId);
    }

    @PostMapping(path = "/")
    public MessageModel addMessage(@RequestBody MessageModel messageModel, @RequestParam Long productId, @RequestParam Long userId, @RequestParam Long receiverId) {
        return messageService.addMessage(messageModel, productId, userId, receiverId);
    }

    @GetMapping(path = "/sender")
    public Iterable<MessageModel> getMessagesBySenderId(@RequestParam Long senderId) {
        return messageService.getMessagesBySenderId(senderId);
    }

    @GetMapping(path = "/receiver")
    public Iterable<MessageModel> getMessagesByReceiverId(@RequestParam Long receiverId) {
        return messageService.getMessagesByReceiverId(receiverId);
    }

    @GetMapping(path = "/highestBid")
    public Object getMessagesByHighestBid(@RequestParam Long productId) {
        return messageService.getMessagesFromHighestBidder(productId);
    }

    @GetMapping(path = "/senderAndProduct")
    public Iterable<MessageModel> getMessagesBySenderAndProduct(@RequestParam Long productId, @RequestParam Long senderId) {
        return messageService.getMessagesByProductAndSender(productId, senderId);
    }
}
