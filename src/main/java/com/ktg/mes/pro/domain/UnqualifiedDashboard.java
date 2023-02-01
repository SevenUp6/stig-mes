package com.ktg.mes.pro.domain;


public class UnqualifiedDashboard  {
    private String  unqualified;
    private String  qualified;
    private String  total;
    private String  product_name;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private String  product_code;

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(String unqualified) {
        this.unqualified = unqualified;
    }
}
