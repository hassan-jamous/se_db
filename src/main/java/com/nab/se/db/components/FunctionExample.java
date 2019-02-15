package com.nab.se.db.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
public class FunctionExample {
    @Autowired
    private SimpleJdbcCall simpleJdbcCall;

    public Double getAccountBalance(String accountMid) throws Exception {

        double value = (double) this.simpleJdbcCall.getJdbcTemplate().query("select pkgCommonSE.fnGetAccountBalance(?, to_date(?,'yyyy-MM-dd')) as xxx from dual",
                new Object[]{accountMid, new SimpleDateFormat("yyyy-MM-dd").format(new Date())},
                new ResultSetExtractor() {
                    @Override
                    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (rs.next()) {
                            return rs.getDouble(1);
                        }
                        return null;
                    }
                });

        return value;

    }
}
