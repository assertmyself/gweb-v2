//off checkstyle
package com.gbcom.system.domain;

import com.gbcom.system.domain.base.BaseTimePlan;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: fengjing
 * Date: 14-7-10
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
public class TimePlan extends BaseTimePlan{

    /**
     * 定时器所属类型：
     * 数据库定时备份
     */
    public static final int TIME_PLAN_OWNERTYPE_DB_BACKUP = 1;

    /**
     * 定时类型:
     *  一次性任务
     *  每天重复
     *  每周重复
     *  每月重复
     *  每隔N分钟重复
     */
    public static final int TIME_PLAN_TYPE_ONE = 1;
    public static final int TIME_PLAN_TYPE_DAY = 2;
    public static final int TIME_PLAN_TYPE_WEEK = 3;
    public static final int TIME_PLAN_TYPE_MONTH = 4;
    public static final int TIME_PLAN_TYPE_MINUTE = 5;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public TimePlan() {
        super();

        this.setType(TimePlan.TIME_PLAN_TYPE_ONE);
        this.setOwnerType(TimePlan.TIME_PLAN_OWNERTYPE_DB_BACKUP);

        Calendar calendar=Calendar.getInstance();
        this.setBeginTime(new java.sql.Timestamp(calendar.getTimeInMillis()));

        this.setRepeatTime("00:00:00");
        this.setSelectWeek("");
        this.setSelectDay("");

        this.setIntervalTime(60);
        this.setState(true);
    }

    /**
     * Constructor for primary key
     */
    public TimePlan(Long id) {
        super(id);
    }
    /*[CONSTRUCTOR MARKER END]*/
}
