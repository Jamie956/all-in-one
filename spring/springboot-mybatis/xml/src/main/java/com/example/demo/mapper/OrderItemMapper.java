package com.example.demo.mapper;

import com.example.demo.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderItemMapper {
	void create(OrderItem orderItem);
	void deleteOrderItem();
}
