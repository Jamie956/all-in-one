package com.example.demo.dao;

import com.example.demo.entity.Product;
import com.example.demo.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

	@Autowired
	private ProductMapper productMapper;

	@Test
	public void listOneToMany() {
		List<Product> ps = productMapper.findJoinProduct();
		for (Product p : ps) {
			System.out.println(p + " 对应的分类是 \t " + p.getCategory());
		}
	}

	@Test
	public void listByIf() {
		System.out.println("===name = null===");
		List<Product> ps = productMapper.findByIf();
		for (Product p : ps) {
			System.out.println(p);
		}

		System.out.println("===name != null===");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		List<Product> ps2 = productMapper.findByIf(params);
		for (Product p2 : ps2) {
			System.out.println(p2);
		}
	}

	@Test
	public void listBywhere() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
//		 params.put("price", "90");

		List<Product> ps = productMapper.findByWhere(params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	@Test
	public void updateBySet() {
		Product p = new Product();
		p.setId(6);
		p.setName("product aj");
		p.setPrice(99.99f);
		productMapper.updateBySet(p);
	}

	@Test
	public void listByTrimWhere() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		// params.put("price", "90");

		List<Product> ps = productMapper.findByTrimWhere(params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	@Test
	public void updateByTrimSet() {
		Product p = new Product();
		p.setId(6);
		p.setName("product bf");
		p.setPrice(9.99f);
		productMapper.updateByTrimSet(p);
	}

	@Test
	public void listByWhenOtherwise() {
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("name","a");
		// params.put("price","10");
		List<Product> ps = productMapper.findByWhenOtherwise(params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	@Test
	public void listByForeach() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(3);
		ids.add(5);
		List<Product> ps = productMapper.findByForeach(ids);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	@Test
	public void listByBind() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "a");
		List<Product> ps = productMapper.findByBind(params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

}
