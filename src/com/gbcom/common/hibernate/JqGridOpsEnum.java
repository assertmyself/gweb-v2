//off checkstyle
package com.gbcom.common.hibernate;

/**
 * 查询条件 操作 enum
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午01:20:14
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.hibernate.JqGridOpsEnum
 */
public enum JqGridOpsEnum {

    EQ("eq", "equal", "="),
    NE("ne", "not equal", "<>"),
    LT("lt", "less", "<"),
    LE("le", "less or equal", "<="),
    GT("gt", "greater", ">"),
    GE("ge", "greater or equal", ">="),
    BW("bw", "begins with", "LIKE"),
    BN("bn", "does not begin with", "NOT LIKE"),
    IN("in", "in", "IN"),
    NI("ni", "not in", "NOT IN"),
    EW("ew", "ends with", "LIKE"),
    EN("en", "does not end with", "NOT LIKE"),
    CN("cn", "contains", "LIKE"),
    NC("nc", "does not contain", "NOT LIKE"),
    NU("nu", "is null", "IS NULL"),
    NN("nn", "is not null", "IS NOT NULL");

    String code;

    String description;

    String operator;

    private JqGridOpsEnum(String code, String description, String operator) {
        this.code = code;
        this.description = description;
        this.operator = operator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public static JqGridOpsEnum getJqGridOpsEnumByCode(String code) {
        JqGridOpsEnum[] jqGridOpsEnums = values();
        for (int i = 0; i < jqGridOpsEnums.length; i++) {
            if (jqGridOpsEnums[i].code.equals(code)) {
                return jqGridOpsEnums[i];
            }
        }
        return null;
    }
}
