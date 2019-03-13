package com.nab.se.db.components;

import com.nab.se.db.domains.*;
import com.nab.se.db.nonFunctional.aspects.annotations.LogMe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @LogMe
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
                new MapSqlParameterSource().addValue("accountMid" , acountMid),
                new BeanPropertyRowMapper<>(FundStrategy.class));

    }


    @LogMe
    public List<BeneficiaryDetails> getBeneficiaryDetails(String accountMid) {

        String sql =
                "SELECT ab.firstname AS firstName," +
                        " ab.lastname AS lastName," +
                        " TO_CHAR(ab.date_of_birth , 'YYYY-MM-DD') AS dateOfBirth," +
                        " ab.relation AS relationship," +
                        " ab.ben_pct as portion," +
                        " ab.ben_type AS beneficiaryType," +
                        " rownum as r" +
                        " FROM account_beneficiary ab" +
                        " WHERE ab.account_mid = :accountMid" +
                        " AND ab.EXPIRE_YN = 'N'";


        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(BeneficiaryDetails.class));

    }

    @LogMe
    public List<AdviserDetails> getAccountAdviserDetails(String accountMid) {

        String sql =
                "SELECT  NVL(i.given_name_preferred, i.given_name) AS firstName," +
                        "i.middle_name AS middleName ," +
                        "NVL(i.surname_preferred, i.surname) AS lastName," +
                        "NVL(o.organisation_name_preferred, organisation_name)  AS organisationName," +
                        "TO_CHAR(i.date_of_birth, 'YYYY-MM-DD')  AS dateOfBirth," +
                        "pkgAccountSE.fnGetAdviserPhone( adv.party_mid) AS  phone," +
                        "pkgAccountSE.fnGetAdviserEmail( adv.party_mid) AS email," +
                        "ab.expire_date AS expiryDate" +
                        " FROM account_broker ab," +
                        "broker b," +
                        "client_account ca," +
                        "party_source ps," +
                        "client c," +
                        "account acc," +
                        "advisor adv," +
                        "individual i," +
                        "organisation o," +
                        "account_source accs" +
                        " WHERE acc.account_mid = :accountMid" +
                        " AND  accs.account_mid=acc.account_mid" +
                        " AND ab.account_mid = acc.account_mid" +
                        " AND ab.broker_mid = b.broker_mid" +
                        " AND ca.client_mid = c.client_mid" +
                        " AND b.advisor_mid = adv.advisor_mid" +
                        " AND adv.party_mid = i.party_mid (+)" +
                        " AND adv.party_mid = o.party_mid (+)" +
                        " AND NOT EXISTS " +
                        " (SELECT 1 FROM broker b1, broker_source bs, broker_code_exclusion bce " +
                        " WHERE b1.broker_mid = bs.broker_mid AND bs.broker_fid = bce.broker_id AND b1.advisor_mid = adv.advisor_mid) " +
                        "AND ab.account_broker_role_lid in (1, 2) AND ab.expire_yn = 'N' AND  ca.expire_YN = 'N'" +
                        " AND ( acc.account_status_lid = 1 OR ( acc.account_status_lid = 2 AND  acc.stop_date < Sysdate AND acc.stop_date > add_months(sysdate, - 12 )))" +
                        " AND ca.account_mid = ab.account_mid" +
                        " AND c.party_mid = ps.party_mid" +
                        " AND ca.account_client_role_lid IN   (20,108)" +
                        " and b.broker_status_lid = 1" +
                        " AND ab.expire_date is NULL " +
                        " AND ps.source_system_lid IN (3, 6)";


        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(AdviserDetails.class));

    }

    @LogMe
    public List<AuthRepDetails> getAuthRepDetails(String accountMid) {

        String sql = "SELECT DISTINCT i.given_name firstName," +
                "i.middle_name AS middleName," +
                "i.surname AS lastName," +
                "org.organisation_name organisationName," +
                "pkgAccountSE.fnGetAuthPhone (c2.party_mid)AS phone," +
                "pkgAccountSE.fnGetAuthEmail(c2.party_mid) AS email," +
                "TO_CHAR(i.date_of_birth, 'YYYY-MM-DD')  AS dateOfBirth," +
                "to_char(ca.expire_date, 'YYYY-MM-DD') AS  expiryDate" +
                " FROM client c" +
                " JOIN client_account ca on c.client_mid = ca.client_mid" +
                " JOIN client_account ca2 on ca.account_mid = ca2.account_mid" +
                " JOIN client c2 on c2.client_mid = ca2.client_mid" +
                " JOIN party_source ps on ps.party_mid = c.party_mid" +
                " LEFT JOIN individual i on c2.party_mid = i.party_mid" +
                " LEFT JOIN organisation org on c2.party_mid = org.party_mid" +
                " WHERE ca.account_mid = :accountMid" +
                " AND ca.account_client_role_lid IN (20,108)" +
                "AND ca2.account_client_role_lid IN (109)" +
                "AND ca2.expire_yn = 'N'" +
                "AND ca.expire_yn = 'N'" +
                "AND ps.source_system_lid IN (3,6)";

        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(AuthRepDetails.class));

    }


    @LogMe
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

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().
                            addValue("productType" , productType).addValue("accountMid" , accountMid),
                    new BeanPropertyRowMapper<>(RegularIncomePaymentDetails.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @LogMe
    public IncomeLevel getAccountIncomeLevel(int productType) {
        int randomRow = new Random().nextInt(2000) + 1;
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
                " AND ROWNUM < 9000)" +
                " where r = " + randomRow;

        try {
            return namedParameterJdbcTemplate.queryForObject(sql,
                    new MapSqlParameterSource().addValue("productType" , productType),
                    new BeanPropertyRowMapper<>(IncomeLevel.class));
        } catch (Exception e) {
            return new IncomeLevel();
        }

    }

    @LogMe
    public PreservationDetails getAccountPreservationDetails(int productType, String accountMid) {

        String sql = " SELECT NVL(a.component_type,'X') AS componentType," +
                " ROUND(NVL(bpr.restricted_amount,0),2) AS restrictedNonPreserved," +
                " ROUND(NVL(bpr.unrestricted_amount,0),2) AS unrestrictedNonPreserved," +
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
                " AND nvl(bpr.effective_date,'01-JAN-1900') = (select nvl(max(effective_date),'01-JAN-1900') from balance_preservation WHERE account_mid = :accountMid)" +
                " And ROWNUM = 1";

        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().
                        addValue("productType" , productType).addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(PreservationDetails.class));

    }


    @LogMe
    public CustomerInformation getCustomerInformation(int productType) {
        int randomRow = new Random().nextInt(3989) + 1;
        String sql = "select * from (" +
                "SELECT acm.account_mid as accountMid," +
                " acm.customer_number as customerNumber," +
                " acm.account_number as accountIdDisplay," +
                " rownum as r" +
                " FROM advisor_client_mview acm" +
                " WHERE acm.product_mid = :productType" +
                " AND ROWNUM < 4000)" +
                " where r = " + randomRow;


        return namedParameterJdbcTemplate.queryForObject(sql,
                new MapSqlParameterSource().addValue("productType" , productType),
                new BeanPropertyRowMapper<>(CustomerInformation.class));


    }


    @LogMe

    public List<FutureInvestmentStrategy> getFutureInvestmentStrategy(String accountMid) {
        String sql = "SELECT invt.investment_dsp_name AS fundName, " +
                "acis.percentage AS percentage " +
                "FROM account_strategy acst," +
                "account_invest_strategy acis," +
                "investment_unit_type    invt," +
                "account a" +
                " WHERE acst.account_mid= :accountMid" +
                " AND acst.account_mid = a.account_mid" +
                " AND acst.account_strategy_lid    = 10 " +
                " AND acst.account_strategy_mid    = acis.account_strategy_mid" +
                " AND acst.expire_yn = 'N'" +
                "AND acis.expire_yn               = 'N'" +
                "AND acis.investment_mid          = invt.investment_mid" +
                " AND invt.unit_type_lid           = 0" +
                " AND a.product_mid NOT IN ('38','39')" +
                " UNION SELECT   iut.investment_dsp_name AS FundName," +
                "pgis.Percentage AS allocationPct " +

                " FROM  investment_unit_type iut," +
                "party_group_invest_strategy pgis," +
                "party_group_source_account pgsac," +
                "account a" +
                " WHERE a.account_mid               = :accountMid" +
                " AND   pgsac.account_mid           = a.account_mid" +
                " AND   pgis.party_group_source_mid = pgsac.party_group_source_mid" +
                " AND   iut.investment_mid          = pgis.investment_mid" +
                " AND a.product_mid NOT IN ('38','39')" +
                "AND   iut.unit_type_lid = 0" +
                " AND   NOT EXISTS  (SELECT 1" +
                " FROM account_strategy acst," +
                " account_invest_strategy acis" +
                " WHERE acst.account_mid             = :accountMid" +
                " AND acst.account_strategy_mid    = acis.account_strategy_mid" +
                " AND acst.account_strategy_lid    = 10" +
                " AND acst.expire_yn               = 'N'" +
                " AND acis.expire_yn               = 'N' )";


        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(FutureInvestmentStrategy.class));

    }


    @LogMe

    public List<DrawdownStrategy> getDrawdownStrategy(String accountMid) {
        String sql = "SELECT invt.investment_dsp_name AS fundname," +
                "acss.sequence AS sequence" +
                " FROM account_sell_sequence acss," +
                "investment_unit_type invt" +
                " WHERE acss.account_mid = :accountMid" +
                " AND acss.expire_yn = 'N'" +
                "AND acss.investment_mid = invt.investment_mid" +
                " AND acss.sequence != 0";


        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(DrawdownStrategy.class));

    }

    @LogMe

    public List<BankAccountDetails> getBankAccountDetails(String accountMid) {
        String sql = "SELECT" +
                "     pkgUtility.fnGetDomainValue( 'FACILITY',acfi.facility_attached_lid)" +
                "                                AS usage," +
                "     fins.financial_inst_name   AS bankName," +
                "     fins.branch_name           AS branch," +
                "     fins.financial_inst_code   AS bsb," +
                "     fins.account_number        AS accountNumber," +
                "     fins.account_name          AS accountName," +
                "     acfi.account_mid" +
                " FROM acct_financial_institution acfi," +
                "    financial_institution      fins," +
                "    unl_domain_mapping         unl" +
                " WHERE acfi.account_mid                     = :accountMid" +
                "     AND acfi.financial_institution_mid       = fins.financial_institution_mid" +
                "     AND fins.start_date                     <= TRUNC(SYSDATE)" +
                "     AND NVL(fins.end_date,TRUNC(SYSDATE+1)) >= TRUNC(SYSDATE)" +
                "     AND acfi.facility_attached_lid = unl.domain_lid" +
                "     AND unl.source_system_fid='COMPASS'" +
                "     AND unl.domain_name='FACILITY'" +
                "     AND unl.SOURCE_SYSTEM_VALUE NOT LIKE 'RIP%'" +
                "     AND unl.SOURCE_SYSTEM_VALUE NOT LIKE 'RIF%'" +
                " UNION" +
                " SELECT" +
                "     pkgUtility.fnGetDomainValue( 'FACILITY',acfi.facility_attached_lid)" +
                "                                AS usage," +
                "     fins.financial_inst_name   AS bankName," +
                "     fins.branch_name           AS branchName," +
                "     fins.financial_inst_code   AS bankBSB," +
                "     fins.account_number        AS accountNumber," +
                "     fins.account_name          AS accountName," +
                "     a.account_mid" +
                " FROM acct_financial_institution acfi," +
                "     financial_institution      fins," +
                "     unl_domain_mapping         unl," +
                "     account                    a" +
                " WHERE acfi.account_mid                     = :accountMid" +
                "     AND acfi.financial_institution_mid       = fins.financial_institution_mid" +
                "     AND fins.start_date                     <= TRUNC(SYSDATE)" +
                "     AND NVL(fins.end_date,TRUNC(SYSDATE+1)) >= TRUNC(SYSDATE)" +
                "     AND acfi.facility_attached_lid = unl.domain_lid" +
                "     AND unl.source_system_fid='COMPASS'" +
                "     AND unl.domain_name='FACILITY'" +
                "     AND unl.SOURCE_SYSTEM_VALUE  LIKE 'RIP%'" +
                "     AND a.account_mid = acfi.account_mid" +
                " UNION" +
                " SELECT" +
                "     pkgUtility.fnGetDomainValue( 'FACILITY',acfi.facility_attached_lid)" +
                "                                AS usage," +
                "     fins.financial_inst_name   AS bankName," +
                "     fins.branch_name           AS branchName," +
                "     fins.financial_inst_code   AS bankBSB," +
                "     fins.account_number        AS accountNumber," +
                "     fins.account_name          AS accountName," +
                "     a.account_mid" +
                " FROM acct_financial_institution acfi," +
                "     financial_institution      fins," +
                "     unl_domain_mapping         unl," +
                "     account                    a" +
                " WHERE acfi.account_mid                     = :accountMid" +
                "     AND acfi.financial_institution_mid       = fins.financial_institution_mid" +
                "     AND fins.start_date                     <= TRUNC(SYSDATE)" +
                "     AND NVL(fins.end_date,TRUNC(SYSDATE+1)) >= TRUNC(SYSDATE)" +
                "     AND acfi.facility_attached_lid = unl.domain_lid" +
                "     AND unl.source_system_fid='COMPASS'" +
                "     AND unl.domain_name='FACILITY'" +
                "     AND unl.SOURCE_SYSTEM_VALUE  LIKE 'RIF%'" +
                "         AND a.account_mid = acfi.account_mid";



        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(BankAccountDetails.class));

    }


    @LogMe

    public List<CurrentFundInvestment> getCurrentFundInvestment(String accountMid) {
        String sql = "SELECT  ipa.apir_Code as apirCode," +
                "round(lbi.units * lup.unit_price, 2) balance," +
                "    INITCAP(TO_CHAR(lbi.effective_date, 'DD/MM/YYYY')) asAtDate," +
                "    i.investment_name fundName," +
                "    lup.unit_price unitPrice," +
                "    lbi.units unitsHeld," +
                "    a.account_mid," +
                "    udv.Domain_value fundType" +
                " FROM account a," +
                "    latest_balance_investment lbi," +
                "    latest_unit_price lup," +
                "    investment i," +
                "    investment_product_apir ipa," +
                "    advisor_client_mview acm," +
                "    unl_domain_value udv " +
                "WHERE a.account_mid = :accountMid" +
                "    and lbi.account_mid = a.account_mid" +
                "    and lup.investment_mid = lbi.investment_mid" +
                "    and i.investment_mid = lbi.investment_mid" +
                "    and i.investment_active_yn = 'Y'" +
                "    and ipa.INVESTMENT_MID = lbi.INVESTMENT_MID" +
                "    and acm.account_mid = a.account_mid" +
                "    and acm.product_mid = ipa.product_mid" +
                "    and udv.domain_name = 'UNIT_TYPE'" +
                "    and udv.Domain_lid = lbi.unit_type_lid" +
                "    and lbi.units > 0";


        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(CurrentFundInvestment.class));

    }


    @LogMe

    public List<BpayBillerDetails> getBpayBillerDetails(String accountMid) {
        String sql = "select bc.biller_Code as billerCode," +
                "bc.description as description," +
                "bc.grouptype as group_test," +
                "bc.refnumber_suffix as refNumberSuffix," +
                "concat(aod.bpay_account_number,bc.refnumber_suffix) as refNumber," +
                "aod.bpay_account_number" +
                " from biller_codes bc" +
                " join account a on a.product_mid = bc.product_mid" +
                " join account_other_detail aod on a.account_mid = aod.account_mid" +
                " WHERE a.account_mid = :accountMid";


        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource().addValue("accountMid" , accountMid),
                new BeanPropertyRowMapper<>(BpayBillerDetails.class));

    }



}
