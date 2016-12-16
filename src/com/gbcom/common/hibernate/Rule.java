//off checkstyle
package com.gbcom.common.hibernate;

public class Rule {

    //列名
    private String field;

    //操作符
    private String op;

    private String data;

    //类型（date,string。。。）
    private String searchtype;

    public Rule() {
    }

    public Rule(String field, String op, String data, String searchtype) {
        this.field = field;
        this.op = op;
        this.data = data;
        this.searchtype = searchtype;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSearchtype() {
        return searchtype;
    }

    public void setSearchtype(String searchtype) {
        this.searchtype = searchtype;
    }
}
