package com.fin.oopsproject.Model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "bids")
public class BidModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Long bidId;

    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserModel userId;

    @Column(name = "bid")
    private Long bid;

    @ManyToOne(targetEntity = ProductModel.class)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductModel product;

    @Column(name ="frozen")
    private boolean frozen;

    @Column(name ="time")
    private Date time;

    @Column(name ="activeStatus")
    private boolean active;



    public BidModel(UserModel userId, Long bid, ProductModel product) {
        this.userId = userId;
        this.bid = bid;
        this.product = product;
        this.active = true;
    }

    public BidModel() {

    }

    public Long getBidId() {
        return bidId;
    }
    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
    public Long getUserId() {
        return userId.getUserId();
    }

    public UserModel getUser() {
        return userId;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
    }

    public void setUser(UserModel userId) {
        this.userId = userId;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public ProductModel getProduct() {
        return product;
    }
    public void setActiveStatus(boolean active) {
        this.active = active;
    }

    public boolean getActiveStatus() {
        return active;
    }
}
