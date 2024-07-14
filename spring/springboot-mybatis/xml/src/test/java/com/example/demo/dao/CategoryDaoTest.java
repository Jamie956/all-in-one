package com.example.demo.dao;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoTest {

	@Autowired
	private CategoryMapper categoryMapper;

	@Test
	public void simpleList() {
		PageHelper.offsetPage(0, 2);
		List<Category> cs = categoryMapper.findAll();
		for (Category c : cs) {
			System.out.println(c);
		}
		PageInfo<Category> pageInfo = new PageInfo<Category>(cs);
		System.out.println("总数：" + pageInfo.getTotal());
		System.out.println(pageInfo);
	}

	@Test
	public void simpleInsert() {
		Category c = new Category();
		c.setName("new Category");
		int rs = categoryMapper.create(c);
		System.out.println(rs);
		System.out.println(c.getId());
	}

	@Test
	public void simpleDelete() {
		Category c = new Category();
		c.setId(4);
		int rs = categoryMapper.remove(c);
		System.out.println(rs);
	}

	@Test
	public void simpleFindById() {

		try {
			Category rs = categoryMapper.findById(1);
			System.out.println(rs);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void simpleUpdate() {
		Category c = categoryMapper.findById(1);
		c.setName("update category2");
		int rs = categoryMapper.update(c);
		System.out.println(rs);
	}

	@Test
	public void simpleListByName() {
		List<Category> cs = categoryMapper.findByName("cat");
		for (Category c : cs) {
			System.out.println(c);
		}
	}

	@Test
	public void simpleListIdByName() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		params.put("name", "cat");
		List<Category> cs = categoryMapper.findByIdAndName(params);
		for (Category c : cs) {
			System.out.println(c);
		}
	}

	@Test
	public void listOneToMany() {
		List<Category> cs = categoryMapper.findJoinProduct();
		for (Category c : cs) {
			System.out.println(c);
			List<Product> ps = c.getProducts();
			for (Product p : ps) {
				System.out.println("\t" + p);
			}
		}
	}

	@Test
	public void listByPage() {
		List<Category> cs = categoryMapper.findByPage(1, 2);
		for (Category c : cs) {
			System.out.println(c);
		}
	}

}
