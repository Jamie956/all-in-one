package com.example.demo.dao;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void listManyToMany() {
		List<Order> os = orderMapper.findJoinOrderItemAndProduct();
		for (Order o : os) {
			System.out.println(o.getCode());
			List<OrderItem> ois = o.getOrderItems();
			for (OrderItem oi : ois) {
				System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(),
						oi.getNumber());
			}
		}
	}

	@Test
	public void deleteMulti() {
		orderMapper.removeWithOrderItem(2);
	}
	
	@Test 
	public void findById(){
		Order o = orderMapper.findById(1);
		System.out.println(o.getCode());
		List<OrderItem> ois = o.getOrderItems();
		for(OrderItem oi : ois){
			System.out.println(oi);
		}
	}
}
