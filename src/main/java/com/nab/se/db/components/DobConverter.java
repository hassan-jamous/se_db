package com.nab.se.db.components;

import com.nab.se.db.domains.DateOfBirth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class DobConverter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getDateOfBirth(int partyMid) {

        String sql = "SELECT i.DATE_OF_BIRTH as dateOfBirth FROM INDIVIDUAL i WHERE PARTY_MID = ?";
        String dateOfBirth = jdbcTemplate.queryForObject(sql, new Object[]{partyMid}, String.class);

        if (dateOfBirth != null) {
           return dateOfBirth.substring(0, 10);
        } else{
            return null;
        }
    }

}