package com.fin.oopsproject.Controllers;

import com.fin.oopsproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class BidService  {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BidModel> getAllBids() {
        return bidRepository.findAllBy();
    }

public BidModel addBid(BidModel bidModel, Long productId, Long userId) {
    Date currentDate = new Date(); // Get the current date and time

    ProductModel productModel = productRepository.findById(productId).orElseThrow(() -> 
        new IllegalArgumentException("Product not found"));

    // If the product is not on auction, you can't bid on it
    if (!productModel.getSold().equals("on_auction")) {
        throw new IllegalArgumentException("Product is not on auction");
    }

    // Check if the auction time has passed
    if (currentDate.after(productModel.getExpiryDate())) {
        throw new IllegalArgumentException("Auction time has passed");
    }
    
    UserModel userModel = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("User not found"));

    // Check if the user is the seller
    if (productModel.getUser().getUserId().equals(userModel.getUserId())) {
        throw new IllegalArgumentException("Seller cannot bid on their own product");
    }

    bidModel.setProduct(productModel);
    Long proposedBid = bidModel.getBid();
    
    Long userMoney = userModel.getMoney();

    if (proposedBid > userMoney) {
        throw new IllegalArgumentException("Insufficient funds for this bid");
    }

    BidModel lastBid = productModel.getCurrentBid();

    // If there is a last bid, validate the increment
    if (lastBid != null) {
        if (proposedBid < productModel.getMinimumIncrement() + lastBid.getBid()) {
            throw new IllegalArgumentException("Low increments not allowed");
        }
        
        lastBid.setActiveStatus(false); // Deactivate the last bid
    }

    productModel.setCurrentBid(bidModel);
    bidModel.setUser(userModel);

    return bidRepository.save(bidModel);
}

    
    public Iterable<BidModel> getBidsByUserId(Long userId) {
        UserModel userModel = userRepository.findById(userId).orElse(null);
        assert userModel != null;
        return bidRepository.findAllByUserId(userModel);
    }

    public Iterable<BidModel> getActiveBidsByUserId(Long userId) {
        UserModel userModel = userRepository.findById(userId).orElse(null);
        assert userModel != null;
        return bidRepository.findByActiveTrueAndUserId(userModel);
    }
    
    

    public Long getHighestBid(Long productId) {
        ProductModel productModel = productRepository.findById(productId).orElse(null);
        assert productModel != null;
        List<BidModel> bidModels = bidRepository.findAllByProduct(productModel);
        Long highestBid = null;
        for (BidModel bidModel : bidModels) {
            if (bidModel.getBid() > highestBid) {
                highestBid = bidModel.getBid();
            }
        }
        return highestBid;
    }

    public BidModel freezeBid(Long bidId, Long userId) {
        BidModel bidModel = bidRepository.findById(bidId).orElse(null);
        assert bidModel != null;
        bidModel.setFrozen(true);
        ProductModel productModel = bidModel.getProduct();
        assert productModel != null;
        productModel.setSold("sold");
        productRepository.save(productModel);
        bidRepository.save(bidModel);
        return bidModel;
    }

    public Iterable<ProductModel> getProductsByUserId(Long userId) {
        UserModel userModel = userRepository.findById(userId).orElse(null);
        assert userModel != null;
        Iterable<BidModel> bids = bidRepository.findAllByUserId(userModel);
        HashSet<ProductModel> products = new HashSet<>();
        for (BidModel bid : bids) {
            products.add(bid.getProduct());
        }
        return products;
    }
}
