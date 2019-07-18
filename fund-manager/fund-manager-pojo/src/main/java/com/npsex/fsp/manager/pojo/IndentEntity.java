package com.npsex.fsp.manager.pojo;

import com.npsex.fsp.commons.core.base.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * Created by dongwen on 2017/7/5.
 */
@Alias("IndentEntity")
public class IndentEntity extends BaseEntity {
    /*vbs订单号**/
    private String vbsId;
    /*订单状态**/
    private String orderStatus;
    /*第三方订单号**/
    private String extOrderId;
    /*内部客户号**/
    private String custId;
    /*身份证号**/
    private String idCardNo;
    /*姓名**/
    private String name;



    public String getVbsId() {
        return vbsId;
    }

    public void setVbsId(String vbsId) {
        this.vbsId = vbsId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public void setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "IndentEntity [id=" + id
        + ", vbsId=" + vbsId + ",orderStatus=" + orderStatus + ", extOrderId=" + extOrderId +", custId=" + custId
                + ", idCardNo=" + idCardNo + ", name=" + name +"]";
    }
}
