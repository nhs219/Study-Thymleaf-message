package com.marketProject.controller;

import com.marketProject.jpa.entity.Company;
import com.marketProject.jpa.entity.Product;
import com.marketProject.service.CompanyService;
import com.marketProject.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final CompanyService companyService;

    @PostMapping("/shop/products")
    @ResponseBody
    public Product saveProduct(@Valid @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/companies")
    @ResponseBody
    public Company saveCompany(@Valid @RequestBody Company company) {
        return companyService.saveCompany(company);
    }

    @GetMapping("/companies")
    @ResponseBody
    public List<Company> findCompanies() {
        return companyService.findCompanies();
    }

    @GetMapping("/companies/{companyId}")
    @ResponseBody
    public Company findOne(@PathVariable("companyId") Long companyId) {
        return companyService.findCompany(companyId);
    }
}
