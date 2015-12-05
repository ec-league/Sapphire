package com.sapphire.domain.manage;

import com.sapphire.constant.TicketPriority;
import com.sapphire.constant.TicketType;
import com.sapphire.domain.User;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/23<br/>
 * Email: byp5303628@hotmail.com
 */
@Entity
@Table(name = Ticket.TABLE_NAME)
public class Ticket {
   public static final String TABLE_NAME = "TICKET";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "UIDPK")
   private long uidPk;

   @ManyToOne
   @JoinColumn(name = "CREATE_USER_UID")
   private User createUser;

   @ManyToOne
   @JoinColumn(name = "ASSIGN_USER_UID")
   private User assignUser;

   @Basic
   @Column(name = "TITLE")
   private String title;

   @Basic
   @Column(name = "DESCRIPTION")
   private String description;

   @ManyToOne
   @JoinColumn(name = "PROJECT_UID")
   private Project project;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_TIME")
   private Timestamp createTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "LAST_MODIFY_TIME")
   private Timestamp lastModifyTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "START_TIME")
   private Timestamp startTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "END_TIME")
   private Timestamp endTime;

   @Basic
   @Column(name = "STATUS")
   private int status;

   @Basic
   @Column(name = "TICKET_TYPE")
   private TicketType ticketType;

   @Basic
   @Column(name = "PRIORITY")
   private TicketPriority ticketPriority;

   public TicketType getTicketType() {
      return ticketType;
   }

   public void setTicketType(TicketType ticketType) {
      this.ticketType = ticketType;
   }

   public TicketPriority getTicketPriority() {
      return ticketPriority;
   }

   public void setTicketPriority(TicketPriority ticketPriority) {
      this.ticketPriority = ticketPriority;
   }

   public long getUidPk() {
      return uidPk;
   }

   public void setUidPk(long uidPk) {
      this.uidPk = uidPk;
   }

   public User getCreateUser() {
      return createUser;
   }

   public void setCreateUser(User createUser) {
      this.createUser = createUser;
   }

   public User getAssignUser() {
      return assignUser;
   }

   public void setAssignUser(User assignUser) {
      this.assignUser = assignUser;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Project getProject() {
      return project;
   }

   public void setProject(Project project) {
      this.project = project;
   }

   public Timestamp getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Timestamp createTime) {
      this.createTime = createTime;
   }

   public Timestamp getLastModifyTime() {
      return lastModifyTime;
   }

   public void setLastModifyTime(Timestamp lastModifyTime) {
      this.lastModifyTime = lastModifyTime;
   }

   public Timestamp getStartTime() {
      return startTime;
   }

   public void setStartTime(Timestamp startTime) {
      this.startTime = startTime;
   }

   public Timestamp getEndTime() {
      return endTime;
   }

   public void setEndTime(Timestamp endTime) {
      this.endTime = endTime;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }
}
