package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemDaoTest {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Test
	public void insertManyToMany(){
		Order o = orderMapper.findById(1);
		Product p = productMapper.findByIdJoinProduct(6);
		
		OrderItem oi = new OrderItem();
		oi.setProduct(p);
		oi.setOrder(o);
		oi.setNumber(200);
		
		orderItemMapper.create(oi);
	}

}
