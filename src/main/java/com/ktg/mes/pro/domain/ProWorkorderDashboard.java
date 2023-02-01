package com.ktg.mes.pro.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ktg.common.annotation.Excel;
import com.ktg.common.core.domain.TreeEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产工单对象 pro_workorder
 * 
 * @author yinjinlu
 * @date 2022-05-15
 */
public class ProWorkorderDashboard extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单ID */
    private Long workorderId;



    /** 工单名称 */
    @Excel(name = "工单名称")
    private String workorderName;




    /** 产品编号 */
    @Excel(name = "产品编号")
    private String productCode;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String productSpc;

    /** 单位 */
    @Excel(name = "单位")
    private String unitOfMeasure;


    /** 生产数量 */
    @Excel(name = "生产数量")
    private BigDecimal quantity;

    /** 已生产数量 */
    @Excel(name = "已生产数量")
    private BigDecimal quantityProduced;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requestDate;
    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 延期天数 */
    private String delay;
    /** 良品率 */
    private String quantityQualified;

    public String getQuantityQualified() {
        return quantityQualified;
    }

    public void setQuantityQualified(String quantityQualified) {
        this.quantityQualified = quantityQualified;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    /** 单据状态 */
    @Excel(name = "单据状态")
    private String status;

    /** 预留字段1 */
    private String attr1;

    /** 预留字段2 */
    private String attr2;

    /** 预留字段3 */
    private Long attr3;

    /** 预留字段4 */
    private Long attr4;

    public Long getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(Long workorderId) {
        this.workorderId = workorderId;
    }

    public String getWorkorderName() {
        return workorderName;
    }

    public void setWorkorderName(String workorderName) {
        this.workorderName = workorderName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSpc() {
        return productSpc;
    }

    public void setProductSpc(String productSpc) {
        this.productSpc = productSpc;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getQuantityProduced() {
        return quantityProduced;
    }

    public void setQuantityProduced(BigDecimal quantityProduced) {
        this.quantityProduced = quantityProduced;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public Long getAttr3() {
        return attr3;
    }

    public void setAttr3(Long attr3) {
        this.attr3 = attr3;
    }

    public Long getAttr4() {
        return attr4;
    }

    public void setAttr4(Long attr4) {
        this.attr4 = attr4;
    }

    @Override
    public String toString() {
        return "ProWorkorder{" +
                "workorderId=" + workorderId +
                ", workorderName='" + workorderName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productSpc='" + productSpc + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", quantity=" + quantity +
                ", quantityProduced=" + quantityProduced +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3=" + attr3 +
                ", attr4=" + attr4 +
                '}';
    }
}
