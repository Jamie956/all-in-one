package org.example;

import com.mongodb.client.result.DeleteResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateUnitTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Before
	public void setUp() {
		mongoTemplate.remove(Query.query(Criteria.where("id").is(1)), User.class);
		mongoTemplate.remove(Query.query(Criteria.where("id").is(2)), User.class);

		mongoTemplate.insert(new User(1L, "Tom", "123456", new Date()));
		mongoTemplate.insert(new User(2L, "Jim", "789123", new Date()));
	}

	@Test
	public void findOneTest() {
		Query query = Query.query(Criteria.where("id").is(1));
		User user = mongoTemplate.findOne(query, User.class);
		assert user != null;
		Assert.assertEquals("Tom", user.getName());
	}

	@Test
	public void findByIdTest() {
		User user = mongoTemplate.findById(1, User.class);
		assert user != null;
		Assert.assertEquals("Tom", user.getName());
	}

	@Test
	public void findTest() {
		Query query = Query.query(Criteria.where("name").is("Tom"));
		List<User> users = mongoTemplate.find(query, User.class);
		Assert.assertEquals("Tom", users.get(0).getName());
	}

	@Test
	public void findAllTest() {
		List<User> users = mongoTemplate.findAll(User.class);
		Assert.assertEquals("Tom", users.get(0).getName());
		Assert.assertEquals("Jim", users.get(1).getName());
	}

	@Test
	public void saveOrUpdateTest() {
		User user = new User(2L, "JimUpdated", "123456", new Date());
		User saveUser = mongoTemplate.save(user);
		Assert.assertEquals("JimUpdated", saveUser.getName());
	}

	@Test
	public void aggregateTest() {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.match(Criteria.where("name").is("Tom")),
				Aggregation.project("id", "name"),
				Aggregation.limit(3)
				);
		AggregationResults<User> aggRes = mongoTemplate.aggregate(aggregation, "user", User.class);
		List<User> users = aggRes.getMappedResults();
		Assert.assertEquals("Tom", users.get(0).getName());
	}

	@Test
	public void updateTest() {
		Query query = Query.query(Criteria.where("id").is(1));
		Update update = new Update().set("name", "TomUpdated").set("password", "999");
		mongoTemplate.updateFirst(query, update, User.class);
		User user = mongoTemplate.findById(1, User.class);
		assert user != null;
		Assert.assertEquals("TomUpdated", user.getName());
	}

	@Test
	public void removeTest() {
		Query query = Query.query(Criteria.where("id").is(1));
		DeleteResult deleteResult = mongoTemplate.remove(query, User.class);
		Assert.assertEquals(1, deleteResult.getDeletedCount());
	}

}
