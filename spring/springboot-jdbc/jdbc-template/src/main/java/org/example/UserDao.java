package org.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	private UserRowMapper userMapper = new UserRowMapper();

	public void save(User user) {
		String sql = "INSERT INTO t_user (id, name, password) VALUES (null, ?, ?)";
		Object[] values = { user.getName(), user.getPassword() };
		jdbcTemplate.update(sql, values);
	}

	public List<User> findAll() {
		String sql = "SELECT id, name, password FROM t_user";
		List<User> users = jdbcTemplate.query(sql, userMapper);
		return users;
	}

	public User findAllById(Integer id) {
		String sql = "SELECT id, name, password FROM t_user WHERE id = ?";
		User user = jdbcTemplate.queryForObject(sql, userMapper, id);
		return user;
	}

	public void update(User user) {
		String sql = "UPDATE t_user set name = ?, password = ? where id = ?";
		Object[] values = { user.getName(), user.getPassword(), user.getId() };
		jdbcTemplate.update(sql, values);
	}

	public void removeById(Integer id) {
		String sql = "DELETE FROM t_user WHERE id = ?";
		Object[] values = { id };
		jdbcTemplate.update(sql, values);
	}

	public void cleanup() {
		String sql = "DELETE FROM t_user";
		jdbcTemplate.update(sql);
	}

	public void insertUsers(List<User> users) {
		String sql = "INSERT INTO t_user (id, name, password) VALUES (?, ?, ?)";
		try {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public int getBatchSize() {
					return users.size();
				}

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					Integer id = users.get(i).getId();
					String name = users.get(i).getName();
					String password = users.get(i).getPassword();
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setString(3, password);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
