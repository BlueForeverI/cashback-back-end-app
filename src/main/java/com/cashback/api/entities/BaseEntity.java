package com.cashback.api.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by George on 1.5.2017 г..
 */
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "created", updatable = false)
    protected Date createdDate;

    @Column(name = "updated")
    protected Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    protected void onCreate() {
        setCreatedDate(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedDate(new Date());
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    private void setCreatedDate(Date created) {
        this.createdDate = created;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updated) {
        this.updatedDate = updated;
    }
}
