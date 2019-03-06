package com.nab.se.db.components;

import com.nab.se.db.domains.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class InvestorComponent {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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


    public Address getAddress(String partyMid) {
        String sql = "SELECT ad.address_1 as addressLine1," +
                " ad.address_2 as addressLine2," +
                " ad.address_3 as addressLine3," +
                " ad.suburb," +
                " ad.post_code as postCode" +
                " FROM address ad" +
                " WHERE ad.party_mid = :partyMid" +
                " AND address_type_lid=2";
        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().addValue("partyMid", partyMid),
                    new BeanPropertyRowMapper<>(Address.class));
        } catch (Exception e) {
            return new Address();
        }

    }

    public PostalAddress getPostalAddress(String partyMid) {
        String sql = "SELECT ad.address_1 as addressLine1," +
                " ad.address_2 as addressLine2," +
                " ad.address_3 as addressLine3," +
                " ad.suburb," +
                " ad.post_code as postCode" +
                " FROM address ad" +
                " WHERE ad.party_mid = :partyMid" +
                " AND address_type_lid=1";
        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().addValue("partyMid", partyMid),
                    new BeanPropertyRowMapper<>(PostalAddress.class));
        } catch (Exception e) {
            return new PostalAddress();
        }

    }


}
