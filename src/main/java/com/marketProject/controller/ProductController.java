package com.marketProject.controller;

import com.marketProject.jpa.entity.Product;
import com.marketProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/shop/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 추후에 카테고리별 조회 기능 추가해야함.
//    @GetMapping
//    @ResponseBody
//    public List<Product> findProducts(@RequestParam Map<String, Object> reqParam) {
//        return productService.findProducts(reqParam);
//    }

    @GetMapping
    @ResponseBody
    public List<Product> findProducts(@RequestParam(required = false) String productCode) {
        return productService.findProducts(productCode);
    }

    @GetMapping("/{productCode}")
    @ResponseBody
    public Product findProduct(@PathVariable("productCode") String productCode) {
        return productService.findProduct(productCode);
    }
}
