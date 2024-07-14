package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Sql({"drop_schema.sql", "create_schema.sql"})
@Sql(scripts = "insert_data.sql", statements = "insert into student(id, name) values (100, 'Shiva')")
@SpringJUnitConfig
public class _9_Sql {

    @Configuration
    static class MyConfig {
        @Bean
        public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder().setName("test-db").build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Sql 执行 sql 文件，可以在内存创建表和插入数据
     */
    @Test
    public void test() {
        List<Map<String, Object>> students = jdbcTemplate.queryForList("SELECT * FROM student");
        Assert.assertEquals("Shiva", students.get(0).get("name"));
        Assert.assertEquals("Mohan", students.get(1).get("name"));
        Assert.assertEquals("Krishna", students.get(2).get("name"));
    }
}
