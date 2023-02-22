package org.khasanof.springjdbctemplate.jdbc;

import org.khasanof.springjdbctemplate.entity.AuthUserEntity;
import org.khasanof.springjdbctemplate.rowMapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nurislom
 * <br/>
 * Date: 2/22/2023
 * <br/>
 * Time: 6:09 PM
 * <br/>
 * Package: org.khasanof.springjdbctemplate.jdbc
 */
@Component
public class TemplateExmple implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public TemplateExmple(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("auth_user");
    }

    @Override
    public void run(String... args) throws Exception {
//        selectCount();
//        selectAuthUsers();
//        namedParameterTemplate();
//        newMethodNamedParameterTemplate();
//        rowMapperTemplate();
        setSimpleJdbcInsert();
    }

    public void selectCount() {
        Integer result = jdbcTemplate.queryForObject("select count(*) from auth_user", Integer.class);
        System.out.println("result = " + result);
    }

    public void selectAuthUsers() {
        int update = jdbcTemplate.update("insert into auth_user values (?, ?, ?)",101, "Bill", "Gates");
        System.out.println("update = " + update);
    }

    public void namedParameterTemplate() {
        SqlParameterSource namedParameter = new MapSqlParameterSource().addValue("id", 1);
        String username = parameterJdbcTemplate.queryForObject("select username from auth_user where id = :id",
                namedParameter, String.class);
        System.out.println("username = " + username);
    }

    public void newMethodNamedParameterTemplate() {
        AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setUsername("Bill");

        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(authUserEntity);

        String SELECT_BY_ID = "select username from auth_user where username = :username";

        String result = parameterJdbcTemplate.queryForObject(SELECT_BY_ID, parameterSource, String.class);

        System.out.println("result = " + result);
    }

    public void rowMapperTemplate() {
        String query = "select * from auth_user where id = ?";

        AuthUserEntity user = jdbcTemplate.queryForObject(query, new Object[]{101}, new AuthUserMapper());
        System.out.println("user = " + user);
    }

    public void setSimpleJdbcInsert() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", 102);
        parameters.put("username", "Nurislom");
        parameters.put("password", "2004");

        int execute = simpleJdbcInsert.execute(parameters);
        System.out.println("execute = " + execute);
    }

    public int[] batchUpdateUsingJdbcTemplate(List<AuthUserEntity> list) {
        return jdbcTemplate.batchUpdate("insert into auth_user values (?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, list.get(i).getId());
                        ps.setString(2, list.get(i).getUsername());
                        ps.setString(3, list.get(i).getPassword());
                    }

                    @Override
                    public int getBatchSize() {
                        return list.size();
                    }
                }
        );
    }
}
