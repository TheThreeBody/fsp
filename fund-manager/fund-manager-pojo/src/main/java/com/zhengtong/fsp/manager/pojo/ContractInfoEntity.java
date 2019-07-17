package com.zhengtong.fsp.manager.pojo;

/**
 * Created by songhuiping on 2017/7/26.
 */
public class ContractInfoEntity {
    /**
     * 状态表主键，用于更新状态
     */
    private Long generStatusId;
    /**
     * 唯一uuid
     */
    private String grxxsqsUuid;
    /**
     * 产品类型
     */
    private Byte productType;
    /**
     */
    private String custId;
    /**
     * 客户姓名
     */
    private String custName;
    /**
     * 客户身份证号
     */
    private String custIdNo;
    /**
     * 电话
     */
    private String custMobile;

    /**
     * 授信时间
     */
    private String creditTime;


    public Long getGenerStatusId() {
        return generStatusId;
    }

    public void setGenerStatusId(Long generStatusId) {
        this.generStatusId = generStatusId;
    }

    public Byte getProductType() {
        return productType;
    }

    public void setProductType(Byte productType) {
        this.productType = productType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCreditTime() {
        return creditTime;
    }

    public void setCreditTime(String creditTime) {
        this.creditTime = creditTime;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getGrxxsqsUuid() {
        return grxxsqsUuid;
    }

    public void setGrxxsqsUuid(String grxxsqsUuid) {
        this.grxxsqsUuid = grxxsqsUuid;
    }
}
