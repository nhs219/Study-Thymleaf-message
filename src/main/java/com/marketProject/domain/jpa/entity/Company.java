package com.marketProject.domain.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String companyNum;
    private String email;

    @OneToMany(mappedBy = "company")
    private List<Product> products = new ArrayList<>();
}
