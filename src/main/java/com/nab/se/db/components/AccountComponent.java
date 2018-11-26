package com.nab.se.db.components;

import com.nab.se.db.domains.Adviser;
import com.nab.se.db.domains.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountComponent {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PaymentStrategy getAccountPaymentStrategy(int accountMid, String paymentStrategyType) {
        String sql = " SELECT acst.total_amount AS Amount," +
                " unl.domain_value AS Frequency," +
                " TO_CHAR(acst.start_date , 'DD-MON-YYYY') AS NextPaymentDate," +
                " acst.payee_name AS Payee" +
                " FROM account_strategy acst, unl_domain_value unl" +
                " WHERE acst.account_mid = '2252927'" +
                " AND acst.account_strategy_lid = 21" +
                " AND acst.expire_yn = 'N'" +
                " AND acst.frequency_lid = unl.domain_lid" +
                " AND unl.domain_name = 'FREQUENCY'  " +
                " ORDER BY acst.payee_name";

        return jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) ->
                        new PaymentStrategy(resultSet.getString("Amount"),
                                resultSet.getString("Frequency"),
                                resultSet.getString("NextPaymentDate"),
                                resultSet.getString("Payee")
                        ));

    }

}
