package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void add(List<Staff> staffs) {
        for (Staff staff : staffs) {
            add(staff);
        }
    }

    public void addNoTransaction(List<Staff> staffs) {
        for (Staff staff : staffs) {
            add(staff);
        }
    }

    public void add(Staff staff) {
        String sql = "insert into staff(name) values(:name)";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(staff);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public void update(Staff staff) {
        String sql = "update staff set name=:name where id=:id";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlParameterSource paramSource = new BeanPropertySqlParameterSource(staff);
        namedParameterJdbcTemplate.update(sql, paramSource);
    }

    public void delete(int id) {
        String sql = "delete from staff  where id=?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }

    public void deleteAll() {
        String sql = "delete from staff";
        jdbcTemplate.update(sql);
    }

    public List<Staff> list() {
        return jdbcTemplate.query("select * from staff", new BeanPropertyRowMapper<>(Staff.class));
    }

    public Staff get(int id) {
        String sql = "select * from staff where id=?";
        Object[] params = new Object[]{id};
        List<Staff> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Staff.class));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
