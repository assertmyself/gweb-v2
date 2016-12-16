//off checkstyle
package com.gbcom.common.hibernate;

/**
 * 查询条件，查询组 排序类等
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午01:21:00
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.hibernate.QueryCondition
 */
public class QueryCondition {

    private Group group;

    private String orderColumn;

    private String orderType;

    public QueryCondition() {
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
