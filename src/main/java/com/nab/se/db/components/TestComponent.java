package com.nab.se.db.components;

import com.nab.se.db.domains.Adviser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestComponent {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Adviser getAdviser(int adviserId) {
        String sql = "select given_name, surname from ADVISOR_CLIENT_MVIEW where customer_number = :adviserId";

        return jdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("adviserId", adviserId),
                (resultSet, rowNum) ->
                        new Adviser(
                                resultSet.getString("given_name"),
                                resultSet.getString("surname")
                        ));
    }
}
