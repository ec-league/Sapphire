package com.sapphire.user.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/17<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = Role.TABLE_NAME)
public class Role {
    public static final String TABLE_NAME = "ROLE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UIDPK")
    private long               uidPk;

    @Basic
    @Column(name = "ROLE_NAME")
    private String             roleName;

    @Basic
    @Column(name = "PRIORITY")
    private int                priority;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getUidPk() {
        return uidPk;
    }

    public void setUidPk(long uidPk) {
        this.uidPk = uidPk;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
