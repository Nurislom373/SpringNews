package org.khasanof.springjdbctemplate.rowMapper;

import org.khasanof.springjdbctemplate.entity.AuthUserEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Author: Nurislom
 * <br/>
 * Date: 2/22/2023
 * <br/>
 * Time: 7:22 PM
 * <br/>
 * Package: org.khasanof.springjdbctemplate.rowMapper
 */
@Component
public class AuthUserMapper implements RowMapper<AuthUserEntity> {

    @Override
    public AuthUserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        AuthUserEntity user = new AuthUserEntity();

        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setCreatedAt(rs.getDate("created_at"));

        return user;
    }
}
