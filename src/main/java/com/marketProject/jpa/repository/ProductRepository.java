package com.marketProject.jpa.repository;

import com.marketProject.jpa.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public void save(Product product) {
        if (product.getCode() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public Product findOne (String code) {
        return em.find(Product.class, code);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }
}
