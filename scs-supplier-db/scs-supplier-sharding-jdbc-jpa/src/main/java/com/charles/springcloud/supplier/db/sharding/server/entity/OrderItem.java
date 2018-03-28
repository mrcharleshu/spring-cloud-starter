package com.charles.springcloud.supplier.db.sharding.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long orderItemId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Integer userId;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(final long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.format("order_item_id: %s, order_id: %s, user_id: %s", orderItemId, orderId, userId);
    }
}
