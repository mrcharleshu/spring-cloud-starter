package com.charles.springcloud.supplier.db.sharding;

import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.InlineShardingStrategyConfiguration;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String DRIVER_CLASS_NAME = "net.sf.log4jdbc.DriverSpy";
    private static final String URL_DS_0 = "jdbc:log4jdbc:mysql://localhost:3306/ds_0" +
            "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String URL_DS_1 = "jdbc:log4jdbc:mysql://localhost:3306/ds_1" +
            "?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOne(final Long orderId) {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource1.setUrl(URL_DS_0);
        dataSource1.setUsername(USERNAME);
        dataSource1.setPassword(PASSWORD);
        dataSourceMap.put("ds_0", dataSource1);

        // 配置第二个数据源
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource2.setUrl(URL_DS_1);
        dataSource2.setUsername(USERNAME);
        dataSource2.setPassword(PASSWORD);
        dataSourceMap.put("ds_1", dataSource2);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds_${0..1}.t_order_${0..1}");

        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "ds_${user_id % 2}"));

        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("id", "t_order_${id % 2}"));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        // 省略配置order_item表规则...

        // 获取数据源对象
        DataSource dataSource;
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,
                    new ConcurrentHashMap(), new Properties());
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * from t_order where id = " + orderId);
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setAddress(rs.getString("address"));
            order.setNumber(rs.getString("number"));
            order.setPrice(rs.getDouble("price"));
            order.setConsignee(rs.getString("consignee"));
            order.setConsigner(rs.getString("consigner"));
            order.setUserId(rs.getLong("user_id"));
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
