
package com.charles.springclound.data.sharding.mybatis.repository;

import com.charles.springclound.data.sharding.mybatis.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemRepository {
    void createIfNotExistsTable();

    void truncateTable();

    Long insert(OrderItem model);

    void delete(Long orderItemId);

    List<OrderItem> selectAll();

    void dropTable();
}
