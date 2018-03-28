package com.charles.springclound.supplier.db.sharding.mybatis.repository;

import com.charles.springclound.supplier.db.sharding.mybatis.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRepository {
    void createIfNotExistsTable();

    void truncateTable();

    void dropTable();

    Long insert(Order model);

    void delete(Long orderId);

    Order findOne(@Param(value = "userId") Integer userId, @Param(value = "orderId") Long orderId);
}
