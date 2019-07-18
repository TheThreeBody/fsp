package com.npsex.fsp.manager.pojo;

import com.npsex.fsp.commons.core.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dongwen on 2017/7/5.
 */
public class CustEntity extends BaseEntity{
    /*姓名**/
    private String name;
    /*身份证号**/
    private String idCardNo;
    /*手机号**/
    private String mobile;
    /*最近的银行卡号**/
    private String cardNo;
    /*银行名称**/
    private String bankName;
    /*授信额度**/
    private BigDecimal creditAmount;
    /*额度有效期**/
    private Date creditExpireDate;

    private Date creditExpireDate1;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Date getCreditExpireDate() {
        return creditExpireDate;
    }

    public void setCreditExpireDate(Date creditExpireDate) {
        this.creditExpireDate = creditExpireDate;
    }

    public Date getCreditExpireDate1() {
        return creditExpireDate1;
    }

    public void setCreditExpireDate1(Date creditExpireDate1) {
        this.creditExpireDate1 = creditExpireDate1;
    }
}
