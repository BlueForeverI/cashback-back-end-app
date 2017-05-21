package com.cashback.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by George on 21.5.2017 г..
 */
@Entity
@Table(name = "merchants")
public class Merchant extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String redirectUrl;

    @Column(nullable = false)
    private double cashbackPercentage;

    @Column
    private String imageUrl;

    @Column
    private String contactName;

    @OneToMany(mappedBy = "merchant", targetEntity = Payment.class)
    private List<Payment> payments;

    @OneToMany(mappedBy = "merchant", targetEntity = MerchantPageVisit.class)
    private List<MerchantPageVisit> pageVisits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public double getCashbackPercentage() {
        return cashbackPercentage;
    }

    public void setCashbackPercentage(double cashbackPercentage) {
        this.cashbackPercentage = cashbackPercentage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<MerchantPageVisit> getPageVisits() {
        return pageVisits;
    }

    public void setPageVisits(List<MerchantPageVisit> pageVisits) {
        this.pageVisits = pageVisits;
    }
}
