package com.nab.se.db.components;

import com.nab.se.db.domains.IncomeLevel;
import com.nab.se.db.domains.PaymentStrategy;
import com.nab.se.db.domains.PreservationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountComponent {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PaymentStrategy getAccountPaymentStrategy(int productType) {
        String sql = " SELECT acst.total_amount AS Amount," +
                " unl.domain_value AS Frequency," +
                " TO_CHAR(acst.start_date , 'DD-MON-YYYY') AS NextPaymentDate," +
                " acst.payee_name AS Payee," +
                " acst.ACCOUNT_MID as AccountMid" +
                " FROM account_strategy acst, unl_domain_value unl" +
                " WHERE acst.account_strategy_lid = :productType" +
                " AND acst.expire_yn = 'N'" +
                " AND acst.frequency_lid = unl.domain_lid" +
                " AND unl.domain_name = 'FREQUENCY'  " +
                " And ROWNUM = 1" +
                " ORDER BY acst.payee_name";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("productType", productType),
                (resultSet, rowNum) ->
                        new PaymentStrategy(resultSet.getString("Amount"),
                                resultSet.getString("Frequency"),
                                resultSet.getString("NextPaymentDate"),
                                resultSet.getString("Payee"),
                                resultSet.getString("AccountMid")
                        ));

    }


    public IncomeLevel getAccountIncomeLevel(int productType) {
        String sql = " SELECT ap.gross_annual_income AS grossAnnualIncome," +
                " ap.min_income_level AS minIncomeLevel," +
                " ap.max_income_level as maxIncomeLevel," +
                " udv.domain_value as domainValue," +
                " acct.account_mid as accountMid" +
                " FROM account_pension ap,account acct, account_source acc_s, unl_domain_value udv " +
                " WHERE acc_s.component_type = 'P'" +
                " AND acct.account_mid = ap.account_mid" +
                " AND ap.income_stream_phase_lid = udv.domain_lid" +
                " AND udv.domain_name = 'INCOME_STREAM_PHASE' " +
                " And acct.account_status_lid=1" +
                " And ROWNUM = 1";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("productType", productType),
                new BeanPropertyRowMapper<>(IncomeLevel.class));

    }

    public PreservationDetails getAccountPreservationDetails(int productType) {
        String sql = " SELECT NVL(a.component_type,'X') AS componentType," +
                " ROUND(NVL(bpr.restricted_amount,0),2) AS restrictedAmount," +
                " ROUND(NVL(bpr.unrestricted_amount,0),2) AS unrestrictedAmount," +
                " ROUND(NVL(bpr.tax_free_amt,0),2) AS taxFreeAmount," +
                "TRIM(NVL(bpr.release_condition_yn,'N')) AS releaseConditionYn," +
                "TRIM(NVL(bpr.full_tax_free_yn,'N')) AS fullTaxFreeYn," +
                "NVL(bpr.tax_free_percent,0) AS taxFreePercent," +
                "TRIM(NVL(bpr.capsil_disp_preserv_yn,'N')) AS displayPreservationYn," +
                "bpr.effective_date AS effectiveDate," +
                " a.account_mid AS accountMid" +
                " FROM balance_preservation bpr,account_source a" +
                " WHERE a.account_mid = bpr.account_mid" +
                " AND nvl(bpr.effective_date,'01-JAN-1900') = (select nvl(max(effective_date),'01-JAN-1900') from balance_preservation)" +
                " And ROWNUM = 1";


        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("productType", productType),
                new BeanPropertyRowMapper<>(PreservationDetails.class));
    }


}
