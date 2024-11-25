package com.emard.springecom.controller;

import com.emard.springecom.model.Product;
import com.emard.springecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@CrossOrigin
public class ProductController {
    private final ProductService productService;
    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),OK);
    }
    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Product product = productService.getProductById(id);
        if(product.getId() != null)
            return new ResponseEntity<>(product, OK);
        return new ResponseEntity<>(NOT_FOUND);
    }
    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.getProductById(productId);
        if(product.getId() != null)
            return new ResponseEntity<>(product.getImageData(), OK);
        return new ResponseEntity<>(NOT_FOUND);
    }
    @PostMapping("product")
    public ResponseEntity<?> addProduct(
            @RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product savedProduct = null;
        try {
            savedProduct = productService.addOrUpdateProduct(product, imageFile);
        } catch (IOException e) {
           return new ResponseEntity<>(e.getMessage(), INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedProduct, CREATED);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){
        Product updateProduct = null;
        try {
            updateProduct = productService.addOrUpdateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
        }
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product != null){
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", OK);
        }

        return new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
        List<Product> products = productService.searchProduct(keyword);
        return new ResponseEntity<>(products, OK);
    }
 }
