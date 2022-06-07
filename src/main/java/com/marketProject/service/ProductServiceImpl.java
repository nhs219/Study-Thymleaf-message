package com.marketProject.service;

import com.marketProject.jpa.entity.Company;
import com.marketProject.jpa.entity.Product;
import com.marketProject.jpa.repository.CompanyRepository;
import com.marketProject.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Product saveProduct(Product product) {
        // 회사가 등록된 회사여야 등록 가능
        validateCompany(product.getCompany().getId());

        // 상품 등록
        productRepository.save(product);
        return product;
    }

    private void validateCompany(Long companyId) {
        Company findCompany = companyRepository.findOne(companyId);
        if (findCompany == null) {
            throw new IllegalStateException("등록되지 않은 회사입니다.");
        }
    }

    public List<Product> findProducts(String productCode) {

        return productRepository.findAll();
    }

    public Product findProduct(String productCode) {
        return productRepository.findOne(productCode);
    }
}
