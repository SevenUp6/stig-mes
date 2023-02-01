package com.ktg.mes.md.domain;

import com.ktg.common.annotation.Excel;

public class MdItemDashboardVo {

    private static final long serialVersionUID = 1L;



    @Excel(name = "级别", cellType = Excel.ColumnType.STRING, prompt = "物料分类级别")
    private String itemTypeCode;

    @Excel(name = "物料编码", cellType = Excel.ColumnType.STRING, prompt = "物料编码")
    private String itemCode;
    @Excel(name = "物料名称", cellType = Excel.ColumnType.STRING, prompt = "物料名称")
    private String itemName;
    @Excel(name = "规格型号", cellType = Excel.ColumnType.STRING, prompt = "规格型号")
    private String specification;
    @Excel(name = "计量单位", cellType = Excel.ColumnType.STRING, prompt = "计量单位")
    private String unitOfMeasure;

    public String getItemTypeCode() {
        return itemTypeCode;
    }

    public void setItemTypeCode(String itemTypeCode) {
        this.itemTypeCode = itemTypeCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
