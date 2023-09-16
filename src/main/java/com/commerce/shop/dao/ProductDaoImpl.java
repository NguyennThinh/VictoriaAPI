package com.commerce.shop.dao;

import com.commerce.shop.exception.NotFoundException;
import com.commerce.shop.model.Category;
import com.commerce.shop.model.Product;
import com.commerce.shop.repository.CategoryRepository;
import com.commerce.shop.repository.ProductRepository;
import com.commerce.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDaoImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findProductByCategory(int categoryId, Pageable pageable) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Not found category with id: "+categoryId));
        return productRepository.findAllByCategory(category,pageable).getContent();
    }

    @Override
    public List<Product> findAllProductByCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Not found category with id: "+categoryId));

        return productRepository.findByCategory(category);
    }

    @Override
    public Product findProductById(int id) {

        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found product by id: "+id));
    }

    @Override
    public List<Product> findProductTrending() {

        return productRepository.findProductTrendingIndex();
    }

    @Override
    public List<Product> findProductLatest() {
        return productRepository.findProductLatest();
    }

    @Override
    public List<Product> findOtherProduct() {
        return productRepository.findOtherProduct();
    }

    @Override
    public List<Product> findProductSale() {
        return productRepository.findProductSale();
    }

    @Override
    public List<Product> findAllProduct() {


        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductPage(Pageable pageable) {

        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Product> findSaleProductPage(Pageable pageable) {
        return productRepository.findAllBySale(pageable).getContent();
    }

    @Override
    public   List<Product>  searchProduct(String keyword) {
        return productRepository.searchProductByName(keyword).orElseThrow(() -> new NotFoundException("Not found product"));
    }
}
