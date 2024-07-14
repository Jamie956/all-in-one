package com.example.demo.mapper;

import com.example.demo.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
	List<Product> findJoinProduct();
	Product findByIdJoinProduct(int id);
	List<Product> findByIf();
	List<Product> findByIf(Map<String, Object> params);
	List<Product> findByWhere();
	List<Product> findByWhere(Map<String, Object> params);
	void updateBySet(Product product);
	List<Product> findByTrimWhere(Map<String, Object> params);
	void updateByTrimSet(Product product);
	List<Product> findByWhenOtherwise(Map<String, Object> params);
	List<Product> findByForeach(List<Integer> ids);
	List<Product> findByBind(Map<String, String> params);
}
