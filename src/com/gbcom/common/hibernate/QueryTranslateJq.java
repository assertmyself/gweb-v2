package com.gbcom.common.hibernate;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import flexjson.JSONDeserializer;

/**
 * 该类用于将自定的条件对象condition集合转换成hql
 * 核心类
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午01:21:49
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.hibernate.QueryTranslateJq
 */
public class QueryTranslateJq {

    //基本的查询
    private String basicQry;
    //点
    private static final String dot = ".";

    //空格
    private static final String blank = " ";

    private Group group;

    private QueryCondition queryCondition;

    /**
     * QueryTranslateJq
     * @param basicHql String
     * @param group  group
     */
    public QueryTranslateJq(String basicHql, Group group) {
        this.basicQry = basicHql;
        this.group = group;
    }
    /**
     * QueryTranslateJq 
     * @param basicHql String
     * @param queryCondition QueryCondition
     */
    public QueryTranslateJq(String basicHql, QueryCondition queryCondition) {
        this.basicQry = basicHql;
        this.queryCondition = queryCondition;
        this.group = queryCondition.getGroup();
    }

    /**
     * 根据json格式的字符串进行构造
     * @param basicHql String
     * @param filters String
     */
    public QueryTranslateJq(String basicHql, String filters) {
        this.basicQry = basicHql;
        if (!StringUtils.isEmpty(filters)) {
            this.queryCondition = new JSONDeserializer<QueryCondition>().use(null, QueryCondition.class).deserialize(filters);
            if (this.queryCondition != null) {
                this.group = this.queryCondition.getGroup();
            }
        }
    }

    /**
     * 获取基本hql
     * @return String
     */
    public String getBasicQry() {
        return basicQry;
    }

    /**
     * 修改基本hql
     * @param basicSql String
     */
    public void setBasicQry(String basicSql) {
        this.basicQry = basicSql;
    }

    /**
     * getGroup 
     * @return Group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * setGroup
     * @param group Group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * to verdict the operator whether it is  a Comparison sign.
     *
     * @param condition
     * @return
     */
//    public boolean opIsComparison(Rule condition) {
//        boolean isComparison = false;
//        if (!StringHelper.isEmpty(condition.getOp())) {
//            if (condition.getOp().equals(Constant.OPRATER_EQUAL)) {
//                isComparison = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_UNEQUAL)) {
//                isComparison = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_GREATER)) {
//                isComparison = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_GREATER_AND_EQUAL)) {
//                isComparison = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_LESS)) {
//                isComparison = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_LESS_AND_EQUAL)) {
//                isComparison = true;
//            }
//        }
//        return isComparison;
//    }

    /**
     * to verdict the operator whether it is  contains "null".
     *
     * @param
     * @return
     */
//    public boolean opContainsNull(Rule condition) {
//        boolean containsNull = false;
//        if (!StringHelper.isEmpty(condition.getOp())) {
//            if (condition.getOp().equals(Constant.OPRATER_ISNULL)) {
//                containsNull = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_ISNOTNULL)) {
//                containsNull = true;
//            }
//        }
//        return containsNull;
//    }

//    public boolean opContainsLike(Rule condition) {
//        boolean containsLike = false;
//        if (!StringHelper.isEmpty(condition.getOp())) {
//            if (condition.getOp().equals(Constant.OPRATER_CONTAINS)) {
//                containsLike = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_NOT_CONTAINS)) {
//                containsLike = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_RIGHT_CONTAINS)) {
//                containsLike = true;
//            } else if (condition.getOp().equals(Constant.OPRATER_LEFT_CONTAINS)) {
//                containsLike = true;
//            }
//        }
//        return containsLike;
//    }
    /**
     * 处理数据type
     * @param rule Rule
     * 
     * @return String
     */
    public String processDataType(Rule rule) {
        String code = rule.getOp();
        String operator = JqGridOpsEnum.getJqGridOpsEnumByCode(rule.getOp()).getOperator();

        String data = rule.getData().trim();
        if (data.length() <= 10) {
            data += " 00:00:00";
        }
        //当小于等于（<=）某个时间或者大于(>)某个时间时，必须设置第1个值的最大时间
        if (JqGridOpsEnum.LE.getCode().equals(code) || JqGridOpsEnum.GT.getCode().equals(code)) {
            data = StringUtils.replace(data, "00:00:00", "23:59:59");
        } else if (JqGridOpsEnum.EQ.getCode().equals(code)) {   //当天查询使用like
            data = StringUtils.replace(data, " 00:00:00", " %");
            operator = " like ";
        } else if (JqGridOpsEnum.NE.getCode().equals(code)) {  //非当天查询使用not like
            data = StringUtils.replace(data, " 00:00:00", " %");
            operator = " not like ";
        }
        return rule.getField() + operator + "'" + data + "'";
    }

//    public String containNullToQuery(Rule condition) {
//        return getColumnName(condition) + " " + condition.getOp();
//    }

//    public String containLikeToQuery(Rule condition) {
//        return getColumnName(condition) + " " + condition.getOp().replace("|", condition.getData());
//    }

//    public String betweenQuery(Rule condition) {
//        if (condition.getType().equals(Constant.TYPE_IS_DATETIME)) {
//            String firstValue = StringUtils.replace(condition.getData(), "T", " ");
//            String secondValue = StringUtils.replace(condition.getSecondValue(), "T", " ");
//
//            //当处于某2个时间段时，必须设置第2个值的最大时间
//            if (Constant.OPRATER_BETWEEN.equals(condition.getOp())) {
//                secondValue = StringUtils.replace(secondValue, "00:00:00", "23:59:59");
//            }
//            return getColumnName(condition) + " between '" + firstValue + "' and '" + secondValue + "'";
//        } else {
//            return getColumnName(condition) + " between '" + condition.getData() + "' and '" + condition.getSecondValue() + "'";
//        }
//    }

    /**
     * 把条件集合转化成条件语句
     *
     * @return
     */
//    public String conditionToString() {
//        List<Rule> conditionList = conditions;
//        String queryConditon = "";
//        for (Rule condition : conditionList) {
//            if (opIsComparison(condition)) {
//                queryConditon += comparisonToQuery(condition);
//            } else if (opContainsNull(condition)) {
//                queryConditon += containNullToQuery(condition);
//            } else if (opContainsLike(condition)) {
//                queryConditon += containLikeToQuery(condition);
//            } else if (condition.getOp().equals(Constant.OPRATER_BETWEEN)) {
//                queryConditon += betweenQuery(condition);
//            }
//
//            if (!StringHelper.isEmpty(condition.getOp())) {
//                if (!StringHelper.isEmpty(condition.getAndOr())) {
//                    queryConditon += blank + condition.getAndOr() + blank;
//                } else {
//                    queryConditon += " and ";
//                }
//            }
//        }
//        if (!StringUtils.isEmpty(queryConditon)) {
//            queryConditon = queryConditon.substring(0, queryConditon.length() - 4);           //delete the last join-sign
//        }
//        return queryConditon;
//    }
    /**
     * {@link ToSTring
     * @return String
     */
    public String toString() {
        if (StringUtils.isEmpty(basicQry)) {
            return "";
        }

        int orderByLocation = basicQry.toUpperCase().indexOf("ORDER BY");
        String orderByClase = "";
        String ret = basicQry;
        if (orderByLocation > -1) {
            ret = basicQry.substring(0, orderByLocation);
            orderByClase = basicQry.substring(orderByLocation, basicQry.length());
        } else {
            //默认按第1个字段排序，如果不加排序，那么在分页时会有重复记录出现
            //todo 如果排序的字段对应的值相同，那么在分页时也可能有重复记录，最好是再按ID排序
            orderByClase = "order by 1";
        }
        if (queryCondition != null && StringUtils.isNotEmpty(queryCondition.getOrderColumn()) && StringUtils.isNotEmpty(queryCondition.getOrderType())) {
            orderByClase = "order by " + queryCondition.getOrderColumn() + blank + queryCondition.getOrderType();
        }
        String coditionString = getStringForGroup(this.group);
        if (!StringUtils.isEmpty(coditionString)) {

            if (!"".equals(coditionString)) {
                if (basicQry.toUpperCase().indexOf("WHERE") == -1) {
                    ret = ret + " WHERE ";
                }

                if (!basicQry.endsWith(" ")) {
                    ret = ret + " ";
                }

                if (!ret.trim().toUpperCase().endsWith("WHERE")) {
                    ret = ret + " AND ";
                }

                ret += getStringForGroup(this.group);
            }
        }
        ret += blank + orderByClase;
        return ret;
    }

    /**
     * 将group 转化成hql
     * @param group group 
     * @return String
     */
    public String getStringForGroup(Group group) {
        if (group == null) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        List<Group> groups = group.getGroups();
        s.append("(");
        if (groups != null && groups.size() > 0) {
            for (int i = 0; i < groups.size(); i++) {
                Group gp = groups.get(i);
                if (s.length() > 1) {
                    s.append(" " + gp.getGroupOp() + " ");
                }
                s.append(this.getStringForGroup(gp));
            }
        }

        List<Rule> rules = group.getRules();
        if (rules != null && rules.size() > 0) {
            for (int i = 0; i < rules.size(); i++) {
                Rule rule = rules.get(i);
                if (isNullRule(rule)) {
                    continue;
                }
                if (s.length() > 1) {
                    s.append(" " + group.getGroupOp() + " ");
                }
                s.append(this.getStringForRule(rule));
            }
        }
        s.append(")");
        if ("()".equals(s.toString())) {
            return "";
        } else {
            return s.toString();
        }
    }

    /**
     * 将rule转化成String
     * @param rule Rule
     * @return String
     */
    public String getStringForRule(Rule rule) {
        String code = rule.getOp();
        String operator = JqGridOpsEnum.getJqGridOpsEnumByCode(rule.getOp()).getOperator();
        String ret;
        String val = rule.getData();
        try {
    		//对通过servlet传递过来的数据进行UTF-8编码
    		val = new String(val.getBytes("ISO-8859-1"),"UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

        String[] numtypes = {"int", "integer", "float", "number", "currency"};

        String[] datetypes = {"date", "datetime"};

        if (JqGridOpsEnum.BW.getCode().equals(code) || JqGridOpsEnum.BN.getCode().equals(code)) {
            val = val + "%";
        }
        if (JqGridOpsEnum.EW.getCode().equals(code) || JqGridOpsEnum.EN.getCode().equals(code)) {
            val = "%" + val;
        }
        if (JqGridOpsEnum.CN.getCode().equals(code) || JqGridOpsEnum.NC.getCode().equals(code)) {
            val = "%" + val + "%";
        }
        if (JqGridOpsEnum.IN.getCode().equals(code) || JqGridOpsEnum.NI.getCode().equals(code)) {
            val = " (" + val + ")";
        }

        if (ArrayUtils.indexOf(numtypes, rule.getSearchtype()) != -1 || JqGridOpsEnum.NN.getCode().equals(code) || JqGridOpsEnum.NU.getCode().equals(code)) {
            ret = rule.getField() + " " + operator + " " + val;
        } else if (ArrayUtils.indexOf(datetypes, rule.getSearchtype()) != -1) {
            ret = processDataType(rule);
        } else {
            ret = rule.getField() + " " + operator + " \'" + val + "\'";
//            ret = rule.getField() + " " + operator + " " + val + "";
        }
        return ret;
    }

    /**
     * 处理当值为空但是操作符不允许为空的情况
     *
     * @param rule .
     * @return .
     */
    public boolean isNullRule(Rule rule) {
        return StringUtils.isEmpty(rule.getData()) && !JqGridOpsEnum.NN.getCode().equals(rule.getOp()) && !JqGridOpsEnum.NU.getCode().equals(rule.getOp());
    }
}
