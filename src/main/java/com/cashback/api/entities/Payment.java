package com.cashback.api.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by George on 21.5.2017 г..
 */
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(targetEntity = Merchant.class)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    @OneToOne(targetEntity = MerchantPageVisit.class)
    @JoinColumn(name = "merchant_page_visit_id")
    private MerchantPageVisit pageVisit;

    @Column
    private String merhantOrderId;

    @Column
    private BigDecimal amount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getMerhantOrderId() {
        return merhantOrderId;
    }

    public void setMerhantOrderId(String merhantOrderId) {
        this.merhantOrderId = merhantOrderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public MerchantPageVisit getPageVisit() {
        return pageVisit;
    }

    public void setPageVisit(MerchantPageVisit pageVisit) {
        this.pageVisit = pageVisit;
    }
}
