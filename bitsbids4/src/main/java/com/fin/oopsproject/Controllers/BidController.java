package com.fin.oopsproject.Controllers;

import com.fin.oopsproject.Model.BidModel;
import com.fin.oopsproject.Model.ProductModel;
import com.fin.oopsproject.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bids")
public class BidController {
    @Autowired
    private BidService bidService;

    // Read
    @GetMapping(path = "/")
    public Iterable<BidModel> getAllBids() {
        return bidService.getAllBids();
    }

// Create
    @PostMapping(path = "/")
    public BidModel addBid(@RequestBody BidModel bidModel, @RequestParam Long productId, @RequestParam Long userId) {
        return bidService.addBid(bidModel, productId, userId);
    }

    @GetMapping(path = "/{userId}")
    public Iterable<BidModel> getBidsByUserId(@PathVariable Long userId) {
        return bidService.getBidsByUserId(userId);
    }

    @GetMapping(path = "/highestBid/{productId}")
    public Long getHighestBid(@PathVariable Long productId) {
        return bidService.getHighestBid(productId);
    }

    @PostMapping(path = "/freezeBid")
    public BidModel freezeBid(@RequestParam Long bidId, @RequestParam Long userId) {
        return bidService.freezeBid(bidId, userId);
    }

    @GetMapping(path = "/products/{userId}")
    public Iterable<ProductModel> getBiddedProducts(@PathVariable Long userId) {
        return bidService.getProductsByUserId(userId);
    }
}
