package com.nab.se.db.components;

import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class InvestorComponent {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomerToken getCustomerToken(int productType) {
        int randomRow = new Random().nextInt(298) + 1;
        String sql = "select * from (" +
                "SELECT acm.customer_number AS customerId," +
                " ps.party_mid AS partyMid," +
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
                    new BeanPropertyRowMapper<>(CustomerToken.class));
        } catch (Exception e) {
            return new CustomerToken();
        }
    }

    public Person getPerson(String partyMid) {

        String sql =
          "select i.SALUTATION as title," +
          " TO_CHAR(i.date_of_birth, 'YYYY-MM-DD') AS dateOfBirth," +
          " i.GIVEN_NAME as firstName," +
          " i.surname as lastName," +
          " adl.domain_value as gender" +
          " from individual i" +
          " join unl_domain_value adl on adl.DOMAIN_LID = i.sex_code_lid" +
          " and adl.domain_name = 'SEX'" +
          " WHERE party_mid = :partyMid";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("partyMid", partyMid),
                new BeanPropertyRowMapper<>(Person.class));
    }

    public List<Phones> getPhones(String partyMid) {

        String sql =
                " SELECT c.phone_number AS phoneNumber," +
                        " udv.domain_value as usageType" +
                        " FROM contact c" +
                        " JOIN unl_domain_value udv" +
                        " ON udv.domain_lid = c.contact_type_lid" +
                        " AND domain_name = 'CONTACT_TYPE'" +
                        " WHERE party_mid = :partyMid"+
                        " AND c.phone_number is not null";

        return namedParameterJdbcTemplate.query(sql,
                    new MapSqlParameterSource().addValue("partyMid", partyMid),
                    new BeanPropertyRowMapper<>(Phones.class));
    }


    public List<Addresses> getAddresses(String partyMid) {
        String sql = "SELECT ad.address_1 as addressLine1," +
                " ad.address_2 as addressLine2," +
                " ad.address_3 as addressLine3," +
                " ad.suburb as suburb," +
                " ad.post_code as postCode," +
                " ad.state as state," +
                " ad.country_code as country," +
                " adl.domain_value as usageType" +
                " FROM address ad" +
                " join unl_domain_value adl" +
                " on adl.DOMAIN_LID = ad.address_type_lid" +
                " and adl.domain_name ='ADDRESS_TYPE'" +
                " WHERE ad.party_mid = :partyMid";
        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("partyMid", partyMid),
                new BeanPropertyRowMapper<>(Addresses.class));


    }

    public List<Email> getEmail(String partyMid) {
        String sql = "SELECT c.e_mail as email," +
                "adl.domain_value as usageType" +
                " FROM contact c" +
                " join unl_domain_value adl" +
                " on adl.DOMAIN_LID = c.contact_type_lid" +
                " and adl.domain_name ='CONTACT_TYPE'" +
                " and c.e_mail is not null" +
                " WHERE c.party_mid = :partyMid";
        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("partyMid", partyMid),
                new BeanPropertyRowMapper<>(Email.class));


    }

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


}
