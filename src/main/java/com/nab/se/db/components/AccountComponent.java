package com.nab.se.db.components;

import com.nab.se.db.domains.FundStrategy;
import com.nab.se.db.domains.PersonalContactInformation;
import com.nab.se.db.domains.BusinessPhoneNumber;
import com.nab.se.db.domains.IncomeLevel;
import com.nab.se.db.domains.FullNameInvestor;
import com.nab.se.db.domains.RegularIncomePaymentDetails;
import com.nab.se.db.domains.PreservationDetails;
import com.nab.se.db.domains.AddressInvestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class AccountComponent {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<FundStrategy> getFundStrategy(String acountMid) {
        String sql = " SELECT invt.investment_dsp_name fundName," +
                " DECODE(NVL(acis.draw_down_sequence,0) , 0 , acis.percentage || ' %' ," +
                " acis.draw_down_sequence) drawDown" +
                " FROM account_strategy acst," +
                " account_invest_strategy acis," +
                " investment_unit_type invt" +
                " WHERE acst.account_mid = :accountMid" +
                " AND acst.account_strategy_lid IN (20,21)" +
                " AND acst.account_strategy_mid = acis.account_strategy_mid" +
                " AND acst.expire_yn = 'N'" +
                " AND acis.expire_yn = 'N'" +
                " AND acis.investment_mid = invt.investment_mid" +
                " AND invt.unit_type_lid  = 0" +
                " ORDER BY acis.draw_down_sequence ASC, acis.percentage DESC, invt.investment_dsp_name ASC ";

        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid", acountMid),
                new BeanPropertyRowMapper<>(FundStrategy.class));


    }


    public RegularIncomePaymentDetails getRegularIncomePaymentDetails(int productType, String accountMid) {
        String sql = " SELECT acst.total_amount AS Amount," +
                " unl.domain_value AS Frequency," +
                " TO_CHAR(acst.start_date , 'YYYY-MM-DD') AS NextPaymentDate," +
                " acst.payee_name AS Payee," +
                " acst.ACCOUNT_MID as AccountMid" +
                " FROM account_strategy acst, unl_domain_value unl" +
                " WHERE acst.account_strategy_lid IN (21)" +
                " AND acst.expire_yn = 'N'" +
                " AND acst.frequency_lid = unl.domain_lid" +
                " AND acst.ACCOUNT_MID = :accountMid" +
                " AND unl.domain_name = 'FREQUENCY'  " +
                " And ROWNUM = 1" +
                " ORDER BY acst.payee_name";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().
                        addValue("productType", productType).addValue("accountMid", accountMid),
                (resultSet, rowNum) ->
                        new RegularIncomePaymentDetails(resultSet.getString("Amount"),
                                resultSet.getString("Frequency"),
                                resultSet.getString("NextPaymentDate"),
                                resultSet.getString("Payee"),
                                resultSet.getString("AccountMid")
                        ));

    }


    public IncomeLevel getAccountIncomeLevel(int productType) {
        int randomRow = new Random().nextInt(298) + 1;
        String sql = "select * from (" +
                "SELECT ap.gross_annual_income AS grossAnnIncome," +
                " ap.min_income_level AS minIncomeLevel," +
                " ap.max_income_level as maxIncomeLevel," +
                " udv.domain_value as incomeStreamPhase," +
                " acct.account_mid as accountToken," +
                " acm.customer_number as customerNumber," +
                " acm.account_number as accountIdDisplay," +
                " rownum as r" +
                " FROM account_pension ap,account acct, account_source acc_s, unl_domain_value udv, advisor_client_mview acm" +
                " WHERE acc_s.component_type = 'P'" +
                " AND acct.account_mid = ap.account_mid" +
                " AND acm.account_mid = acct.account_mid" +
                " AND ap.income_stream_phase_lid = udv.domain_lid" +
                " AND acct.account_mid = acc_s.account_mid" +
                " AND udv.domain_name = 'INCOME_STREAM_PHASE' " +
                " AND acm.product_mid = :productType" +
                " And acct.account_status_lid=1" +
                " AND ROWNUM < 300)" +
                " where r = " + randomRow;

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().addValue("productType", productType),
                    new BeanPropertyRowMapper<>(IncomeLevel.class));
        } catch (Exception e) {
            return new IncomeLevel();
        }

    }

    public FullNameInvestor getFullNameInvestor(int productType) {
        int randomRow = new Random().nextInt(298) + 1;
        String sql = "select * from (" +
                "SELECT acm.customer_number AS customerNumber," +
                " acm.account_mid AS accountToken," +
                " acm.given_name AS givenName," +
                " acm.surname AS surname," +
                " ps.party_mid AS partyMid," +
                " cnt.phone_number AS homePhoneNumber," +
                " cnt.e_mail AS email," +
                " TO_CHAR(id.date_of_birth, 'YYYY-MM-DD') AS dateOfBirth," +
                " rownum as r" +
                " FROM advisor_client_mview acm" +
                " JOIN party_source ps" +
                " ON ps.party_fid=acm.customer_number" +
                " JOIN individual id" +
                " ON id.party_mid=ps.party_mid" +
                " JOIN contact cnt" +
                " ON cnt.party_mid=id.party_mid" +
                " AND ROWNUM < 300)" +
                " where r = " + randomRow;

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().addValue("productType", productType),
                    new BeanPropertyRowMapper<>(FullNameInvestor.class));
        } catch (Exception e) {
            return new FullNameInvestor();
        }
    }

    public PersonalContactInformation getPersonalContactInformation(String partyMid) {

        String sql =
                " SELECT c.phone_number AS homeNumber," +
                        " c.e_mail AS email" +
                        " FROM contact c" +
                        " JOIN unl_domain_value udv" +
                        " ON udv.domain_lid = c.contact_type_lid" +
                        " AND domain_name = 'CONTACT_TYPE'" +
                        " AND c.CONTACT_TYPE_LID=1" +
                        " WHERE party_mid = :partyMid";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("partyMid", partyMid),
                new BeanPropertyRowMapper<>(PersonalContactInformation.class));
    }

    public BusinessPhoneNumber getBusinessPhoneNumber(String partyMid) {

        String sql =
                " SELECT c.phone_number AS businessPhoneNumber" +
                        " FROM contact c" +
                        " JOIN unl_domain_value udv" +
                        " ON udv.domain_lid = c.contact_type_lid" +
                        " AND domain_name = 'CONTACT_TYPE'" +
                        " AND c.CONTACT_TYPE_LID=2" +
                        " WHERE party_mid = :partyMid";

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().addValue("partyMid", partyMid),
                    new BeanPropertyRowMapper<>(BusinessPhoneNumber.class));
        } catch (Exception e) {
            return new BusinessPhoneNumber();
        }
    }


    public AddressInvestor getAddressInvestor(String partyMid) {
        String sql = "SELECT ad.address_1 as addressLine1," +
                " ad.address_2 as addressLine2," +
                " ad.address_3 as addressLine3," +
                " ad.suburb," +
                " ad.post_code as postCode" +
                " FROM address ad" +
                " WHERE ad.party_mid = :partyMid";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("partyMid", partyMid),
                new BeanPropertyRowMapper<>(AddressInvestor.class));

    }


    public PreservationDetails getAccountPreservationDetails(int productType, String accountMid) {
        String sql = " SELECT NVL(a.component_type,'X') AS componentType," +
                " ROUND(NVL(bpr.restricted_amount,0),2) AS restrictedAmount," +
                " ROUND(NVL(bpr.unrestricted_amount,0),2) AS unrestrictedAmount," +
                " ROUND(NVL(bpr.tax_free_amt,0),2) AS taxFreeAmount," +
                "TRIM(NVL(bpr.release_condition_yn,'N')) AS releaseConditionYn," +
                "TRIM(NVL(bpr.full_tax_free_yn,'N')) AS fullTaxFreeYn," +
                "NVL(bpr.tax_free_percent,0) AS taxFreePercent," +
                "TRIM(NVL(bpr.capsil_disp_preserv_yn,'N')) AS displayPreservationYn," +
                "bpr.effective_date AS effectiveDate," +
                "a.account_mid AS accountMid" +
                " FROM balance_preservation bpr,account_source a" +
                " WHERE a.ACCOUNT_MID = :accountMid" +
                " AND   a.account_mid = bpr.account_mid" +
                " AND nvl(bpr.effective_date,'01-JAN-1900') = (select nvl(max(effective_date),'01-JAN-1900') from balance_preservation WHERE account_mid =:accountMid)" +
                " And ROWNUM = 1";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().
                        addValue("productType", productType).addValue("accountMid", accountMid),
                new BeanPropertyRowMapper<>(PreservationDetails.class));

    }


}
