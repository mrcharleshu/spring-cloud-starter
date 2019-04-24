package com.charles.springcloud.data.sharding.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "status")
    private String status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("order_id: %s, user_id: %s, status: %s", orderId, userId, status);
    }
}
