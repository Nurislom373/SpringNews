package org.khasanof.springjdbctemplate.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.springjdbctemplate.entity.AuthUserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

/**
 * Author: Nurislom
 * <br/>
 * Date: 08.05.2023
 * <br/>
 * Time: 17:19
 * <br/>
 * Package: org.khasanof.springjdbctemplate.jdbc
 */
@Slf4j
@Component
public class JdbcTemplateAllUpdateMethods {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAllUpdateMethods(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Issue a single SQL update operation (such as an insert, update or delete statement).
     */
    public void firstInsertExample() {
        int update = jdbcTemplate.update("insert into auth_user (username, password) values ('nurislom', '123')");
        log.info("firstInsertExample method insert count : {}", update);
    }

    /**
     * Issue a single SQL update operation (such as an insert, update or delete statement) via a prepared statement,
     * binding the given arguments
     */
    public void secondInsertExample() {
        int update = jdbcTemplate.update("insert into auth_user (username, password) values (?, ?)",
                "jeck", "12321");
        log.info("secondInsertExample method insert count : {}", update);
    }

    /**
     * Issue an update statement using a PreparedStatementSetter to set bind parameters, with given SQL. Simpler than
     * using a PreparedStatementCreator as this method will create the PreparedStatement: The PreparedStatementSetter
     * just needs to set parameters.
     */
    public void thirdInsertExample() {
        int update = jdbcTemplate.update("insert into auth_user (username, password) values (?, ?)", (ps) -> {
            ps.setString(1, "Lucy");
            ps.setString(2, "Lucy56789");
        });
        log.info("thirdInsertExample method insert count : {}", update);
    }

    /**
     * Issue a single SQL update operation (such as an insert, update or delete statement) using a
     * PreparedStatementCreator to provide SQL and any required parameters.
     * <p>
     * A PreparedStatementCreator can either be implemented directly or configured through a PreparedStatementCreatorFactory.
     */
    public void fourthInsertExample() {
        final String INSERT = "insert into auth_user (username, password, createdat) values (?, ?, ?)";
        int update = jdbcTemplate.update(connection -> {
            try {
                PreparedStatement statement = connection.prepareStatement(INSERT);
                statement.setString(1, "khasanof");
                statement.setString(2, "nurislom");
                statement.setDate(3, new Date(Instant.now().toEpochMilli()));
                return statement;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        });
        log.info("fourthInsertExample method insert count : {}", update);
    }

    /**
     * Issue an update statement using a PreparedStatementCreator to provide SQL and any required parameters.
     * Generated keys will be put into the given KeyHolder.
     * <p>
     * Note that the given PreparedStatementCreator has to create a statement with activated extraction of generated
     * keys (a JDBC 3.0 feature). This can either be done directly or through using a PreparedStatementCreatorFactory.
     */
    public void fifthInsertExample() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String INSERT = "insert into auth_user (username, password) values (?, ?)";
        int update = jdbcTemplate.update(connection -> {
            try {
                PreparedStatement statement = connection.prepareStatement(INSERT);
                statement.setString(1, "khasanof");
                statement.setString(2, "nurislom");
                return statement;
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }, keyHolder);
        log.info("fifthInsertExample method insert count : {}", update);
    }

    /**
     * Execute multiple batches using the supplied SQL statement with the collect of supplied arguments. The arguments'
     * values will be set using the ParameterizedPreparedStatementSetter. Each batch should be of size indicated in 'batchSize'.
     *
     * @param list enter insert objects list
     */
    public void firstBatchInsertExample(List<AuthUserEntity> list) {
        jdbcTemplate.batchUpdate("insert into auth_user (username, password) values (?, ?)", list,
                list.size(), (PreparedStatement ps, AuthUserEntity entity) -> {
                    ps.setString(1, entity.getUsername());
                    ps.setString(2, entity.getPassword());
                });
        log.info("firstBatchInsertExample method end!");
    }

}
