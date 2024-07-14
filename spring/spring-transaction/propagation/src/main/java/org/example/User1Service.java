package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class User1Service {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(User1 user) {
        String sql = "insert into user1(name) values(:name)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public void deleteAll() {
        String sql = "delete from user1";
        jdbcTemplate.update(sql);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired() {
        User1 user = new User1();
        user.setName("1");
        add(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(){
        User1 user = new User1();
        user.setName("1");
        add(user);
    }

    public List<User1> list() {
        return jdbcTemplate.query("select * from user1", new BeanPropertyRowMapper<>(User1.class));
    }
}
