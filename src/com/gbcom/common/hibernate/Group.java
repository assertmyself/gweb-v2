//off checkstyle
package com.gbcom.common.hibernate;

import java.util.List;

/**
 * 查询组：查询条件的集合
 * 
 * <p>
 * @author syz
 * <p>
 * @date 2015-9-15,下午01:19:26
 * <p>
 * @version v1.0.0
 * <p>
 * @see com.gbcom.common.hibernate.Group
 */
public class Group {

    private String groupOp;

    private List<Rule> rules;

    private List<Group> groups;

    /**
     * Group
     */
    public Group() {
    }

    /**
     * 条件结合
     * @param groupOp
     * @param rules
     * @param groups
     */
    public Group(String groupOp, List<Rule> rules, List<Group> groups) {
        this.groupOp = groupOp;
        this.rules = rules;
        this.groups = groups;
    }

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}

