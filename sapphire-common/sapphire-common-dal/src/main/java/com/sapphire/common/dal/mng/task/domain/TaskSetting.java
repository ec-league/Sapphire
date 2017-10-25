/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.sapphire.common.dal.mng.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;

/**
 *
 * @author yunpeng.byp
 * @version $Id: TaskSetting.java, v 0.1 2017年10月25日 下午4:50 yunpeng.byp Exp $
 */
@Entity
@Table(name = "TASK_SETTING")
public class TaskSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UIDPK")
    private long uidPk;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_MODIFY_DATE")
    private Timestamp lastModifyDate;

    /**
     * Getter method for property <tt>uidPk</tt>.
     *
     * @return property value of uidPk
     */
    public long getUidPk() {
        return uidPk;
    }

    /**
     * Setter method for property <tt>uidPk</tt>.
     *
     * @param uidPk  value to be assigned to property uidPk
     */
    public void setUidPk(long uidPk) {
        this.uidPk = uidPk;
    }

    /**
     * Getter method for property <tt>taskName</tt>.
     *
     * @return property value of taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Setter method for property <tt>taskName</tt>.
     *
     * @param taskName  value to be assigned to property taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Getter method for property <tt>cronExpression</tt>.
     *
     * @return property value of cronExpression
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * Setter method for property <tt>cronExpression</tt>.
     *
     * @param cronExpression  value to be assigned to property cronExpression
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * Getter method for property <tt>lastModifyDate</tt>.
     *
     * @return property value of lastModifyDate
     */
    public Timestamp getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * Setter method for property <tt>lastModifyDate</tt>.
     *
     * @param lastModifyDate  value to be assigned to property lastModifyDate
     */
    public void setLastModifyDate(Timestamp lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}