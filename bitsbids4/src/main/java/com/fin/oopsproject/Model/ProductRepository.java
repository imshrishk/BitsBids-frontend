package com.fin.oopsproject.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findByCategory(String category);

    List<ProductModel> findAllBy();

    List<ProductModel> findAllBySold(boolean sold);

    Iterable<ProductModel> findAllByUser(UserModel userModel);
}
