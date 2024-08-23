package com.fin.oopsproject.Controllers;

import com.fin.oopsproject.Model.*;
import com.fin.oopsproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BidRepository bidRepository;

    public MessageModel addMessage(MessageModel messageModel, Long productId, Long userId, Long receiverId) {
        messageModel.setProduct(productRepository.findById(productId).orElse(null));
        messageModel.setSender(userRepository.findById(userId).orElse(null));
        messageModel.setReceiver(userRepository.findById(receiverId).orElse(null));
        messageModel.setTimestamp(java.time.LocalDateTime.now().toString());
        return messageRepository.save(messageModel);
    }

    public Iterable<MessageModel> getMessagesByProductId(Long productId) {
        return messageRepository.findAllByProductId(productId);
    }

    public Iterable<MessageModel> getAllMessages() {
        return messageRepository.findAll();
    }

    public Iterable<MessageModel> getMessagesBySenderId(Long senderId) {
        UserModel sender = userRepository.findById(senderId).orElse(null);
        assert sender != null;
        return messageRepository.findAllBySender(sender);
    }

    public Iterable<MessageModel> getMessagesByReceiverId(Long receiverId) {
        UserModel receiver = userRepository.findById(receiverId).orElse(null);
        Iterable<ProductModel> products = productRepository.findAllByUser(receiver);
        List<MessageModel> messages = new ArrayList<>();
        for (ProductModel product : products) {
            messages.addAll((List<MessageModel>) messageRepository.findAllByProduct(product));
        }
        return messages;
    }

    public Iterable<MessageModel> getMessagesForUser(Long userId) {
        HashSet<MessageModel> messages = new HashSet<>();
        UserModel user = userRepository.findById(userId).orElse(null);
        assert user != null;
        messages.addAll((List<MessageModel>) messageRepository.findAllBySender(user));
        messages.addAll((List<MessageModel>) messageRepository.findAllByReceiver(user));
        return messages;
    }

    public Object getMessagesFromHighestBidder(Long productId) {
        ProductModel productModel = productRepository.findById(productId).orElse(null);
        assert productModel != null;
        HashSet<MessageModel> messages = new HashSet<>();
        List<BidModel> bids = bidRepository.findAllByProduct(productModel);
        Long highestBid = null;

        for (BidModel bid : bids) {
            if (bid.getBid() > highestBid) {
                highestBid = bid.getBid();
            }
        }
        
        for (BidModel bid : bids) {
            if (bid.getBid() == highestBid) {
                messages.addAll(
                        (List<MessageModel>) messageRepository.findAllByProductAndSender(productModel, bid.getUser()));
                messages.addAll((List<MessageModel>) messageRepository.findAllByProductAndReceiver(productModel,
                        bid.getUser()));
                Map<String, Object> response = new HashMap<>();
                response.put("messages", messages);
                response.put("highestBid", highestBid);
                response.put("bidder", bid.getUser());
                return response;
            }
        }
        return null;

    }

    public Iterable<MessageModel> getMessagesByProductAndSender(Long productId, Long senderId) {
        ProductModel productModel = productRepository.findById(productId).orElse(null);
        assert productModel != null;
        UserModel sender = userRepository.findById(senderId).orElse(null);
        UserModel receiver = userRepository.findById(productModel.getUser().getUserId()).orElse(null);
        assert sender != null;
        assert receiver != null;
        HashSet<MessageModel> messages = new HashSet<>();
        messages.addAll((List<MessageModel>) messageRepository.findAllByProductAndSender(productModel, sender));
        messages.addAll((List<MessageModel>) messageRepository.findAllByProductAndReceiver(productModel, sender));
        return messages;
    }
}
