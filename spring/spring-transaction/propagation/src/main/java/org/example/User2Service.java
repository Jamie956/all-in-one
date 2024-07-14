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
public class User2Service {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(User2 user) {
        String sql = "insert into user2(name) values(:name)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public void deleteAll() {
        String sql2 = "delete from user2";
        jdbcTemplate.update(sql2);
    }

    public List<User2> list() {
        return jdbcTemplate.query("select * from user2", new BeanPropertyRowMapper<>(User2.class));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired() {
        User2 user = new User2();
        user.setName("1");
        add(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredWithException() {
        User2 user = new User2();
        user.setName("1");
        add(user);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(){
        User2 user = new User2();
        user.setName("1");
        add(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewWithException(){
        User2 user = new User2();
        user.setName("1");
        add(user);
        throw new RuntimeException();
    }
}
