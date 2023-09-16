package com.commerce.shop.repository;

import com.commerce.shop.model.Category;
import com.commerce.shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

    @Query("from Product order by quantitySold desc limit 8")
    List<Product> findProductTrendingIndex();

    @Query("from Product order by pid desc limit 8")
    List<Product> findProductLatest();

    @Query("from Product order by function('newid') limit 6")
    List<Product> findOtherProduct();

    @Query("from Product p join Sale s on p.sale.id = s.id where s.endDate >= CURRENT_DATE and s.startDate <= CURRENT_DATE ")
    List<Product> findProductSale();


    @Query("from Product p where p.productName like %:name%")
    Optional< List<Product> > searchProductByName(@Param("name") String name);


    Page<Product> findAllByCategory(Category category,Pageable pageable);

    @Query("from Product p join Sale s on p.sale.id = s.id where s.endDate >= CURRENT_DATE and s.startDate <= CURRENT_DATE ")

    Page<Product> findAllBySale(Pageable pageable);
}
