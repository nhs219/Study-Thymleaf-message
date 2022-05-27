package com.marketProject.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Company extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @Embedded
    private Address address;
    @NotEmpty
    private String companyNum;
    private String email;

    @OneToMany(mappedBy = "company")
    private List<Product> products = new ArrayList<>();
}
