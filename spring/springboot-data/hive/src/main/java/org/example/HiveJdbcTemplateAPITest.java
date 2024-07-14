package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HiveJdbcTemplateAPITest {
    @Autowired
    private JdbcTemplate hiveJdbcTemplate;

    @Test
    public void list() {
        String sql = "select * from t1";
        List<Map<String, Object>> maps = hiveJdbcTemplate.queryForList(sql);
        System.out.println(maps);
    }

    /**
     * 自定义 RowMapper
     */
    @Test
    public void test1() {
        String sql = "select id, goods_name from ods_comments_jd limit 2";
        //重写RowMapper，定义查询结果映射到对象
        List<JdComment> items = hiveJdbcTemplate.query(sql, (ResultSet resultSet, int rowNum) -> {
            JdComment jdComment = new JdComment();
            jdComment.setGoods_id(resultSet.getString("id"));
            jdComment.setComment_id(resultSet.getString("goods_name"));
            return jdComment;
        });
        System.out.println(items);
    }

    /**
     * 查询返回单列数据，SingleColumnRowMapper
     */
    @Test
    public void singleColumnRowMapperTest() {
        String sql = "select id from ods_comments_jd limit 1";
        String s = hiveJdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<>(String.class));
        System.out.println(s);
    }

    /**
     * row mapper 映射到实体类，BeanPropertyRowMapper
     * 查询单个
     */
    @Test
    public void beanPropertyRowMapperTest() {
        String sql = "select comment_id, goods_id, goods_type from ods_comments_jd limit 1";
        JdComment jdComment = this.hiveJdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdComment.class));
        System.out.println(jdComment);
    }

    /**
     * row mapper 映射到实体类，BeanPropertyRowMapper
     * 查询多个
     */
    @Test
    public void beanPropertyRowMapperListTest() {
        String sql = "select comment_id, goods_id, goods_type from ods_comments_jd limit 2";
        List<JdComment> list = this.hiveJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JdComment.class));
        System.out.println();
        list.forEach(System.out::println);
    }

}
