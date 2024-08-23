package com.fin.oopsproject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fin.oopsproject.Model.*;

import java.math.BigDecimal;
import java.util.*;
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;

    public void transferMoney(ProductModel product) {
        UserModel buyer = product.getCurrentBid().getUser();
        UserModel seller = product.getUser();
        BigDecimal amount = BigDecimal.valueOf(product.getCurrentBid().getBid());

        TransactionModel transaction = new TransactionModel(product, buyer, seller, amount, "completed");
        transactionRepository.save(transaction);

        // Add any additional money transfer logic here
    }

    public Iterable<TransactionModel> getAllTrans() {
        return transactionRepository.findAll();
    }

    public TransactionModel addTransaction(TransactionModel transactionModel) {
        return transactionRepository.save(transactionModel);
    }

    public List<TransactionModel> getTransByUserId(Long userId) {
        UserModel user = new UserModel();
        user.setUserId(userId);
        return transactionRepository.findByBuyerOrSeller(user, user);
    }

    public TransactionModel getTransByTransId(Long transId) {
        return transactionRepository.findById(transId).orElse(null);
    }

    public List<TransactionModel> getTransByProductId(Long productId) {
        Optional<ProductModel> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            return transactionRepository.findByProduct(productOpt.get());
        }
        return Collections.emptyList(); // or handle error
    }
    
    
}
