package com.marketProject.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String street;
    private String zipcode;
    private String detailAddress;

    public Address(String zipcode, String street, String detailAddress) {
        this.zipcode = zipcode;
        this.street = street;
        this.detailAddress = detailAddress;
    }
}
