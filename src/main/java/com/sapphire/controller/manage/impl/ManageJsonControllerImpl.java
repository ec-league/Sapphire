package com.sapphire.controller.manage.impl;

import com.sapphire.common.TimeUtil;
import com.sapphire.constant.TicketConstant;
import com.sapphire.domain.manage.Project;
import com.sapphire.domain.manage.Ticket;
import com.sapphire.dto.Dto;
import com.sapphire.dto.JsonDto;
import com.sapphire.dto.ListJsonDto;
import com.sapphire.service.UserService;
import com.sapphire.service.manager.ProjectService;
import com.sapphire.service.manager.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/rest/manage")
public class ManageJsonControllerImpl {
   private static Logger logger = LoggerFactory
         .getLogger(ManageJsonControllerImpl.class);

   @Autowired
   private ProjectService projectService;
   @Autowired
   private TicketService ticketService;
   @Autowired
   private UserService userService;

   @RequestMapping("/project/save.ep")
   public @ResponseBody JsonDto saveProject(@RequestBody ProjectDto projectDto) {
      try {
         Project project = convertProjectDto(projectDto);
         projectService.saveProject(project);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/project/list.ep")
   public @ResponseBody JsonDto getAllProjects() {
      return new JsonDto().formSuccessDto();
   }

   @RequestMapping("/project/snapshots/list.ep")
   public @ResponseBody JsonDto getAllProjectSnapshots() {
      try {
         List<Project> projects = projectService.getAllProjects();
         List<ProjectSnapshotDto> dtos = new ArrayList<ProjectSnapshotDto>();
         for (Project project : projects) {
            dtos.add(new ProjectSnapshotDto(project));
         }
         return new ListJsonDto<ProjectSnapshotDto>(dtos).formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/ticket/{userId}/getByUser.ep")
   public @ResponseBody JsonDto getTicketsByUserId(
         @PathVariable("userId") long userId) {
      try {
         List<TicketItemDto> dtos = new ArrayList<TicketItemDto>();
         for (Ticket ticket : ticketService.getTicketsByUserId(userId)) {
            dtos.add(new TicketItemDto(ticket));
         }
         return new ListJsonDto<TicketItemDto>(dtos).formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/ticket/{projectId}/getByProject.ep")
   public @ResponseBody JsonDto getTicketsByProjectId(
         @PathVariable("projectId") long projectId) {
      try {
         List<TicketItemDto> dtos = new ArrayList<TicketItemDto>();
         for (Ticket ticket : ticketService.getTicketsByProjectId(projectId)) {
            dtos.add(new TicketItemDto(ticket));
         }
         return new ListJsonDto<TicketItemDto>(dtos).formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/ticket/save.ep")
   public @ResponseBody JsonDto saveTicket(@RequestBody TicketDto ticketDto) {
      try {
         Ticket ticket = convertTicketDto(ticketDto);
         ticketService.saveTicket(ticket);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }

   private static class ProjectDto implements Dto {
      private long projectId;
      private String title;
      private String repoUrl;
      private String description;

      public long getProjectId() {
         return projectId;
      }

      public void setProjectId(long projectId) {
         this.projectId = projectId;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getRepoUrl() {
         return repoUrl;
      }

      public void setRepoUrl(String repoUrl) {
         this.repoUrl = repoUrl;
      }

      public String getDescription() {
         return description;
      }

      public void setDescription(String description) {
         this.description = description;
      }
   }

   private Project convertProjectDto(ProjectDto projectDto) {
      Project project;
      if (projectDto.getProjectId() == 0) {
         project = new Project();
         project.setCreateTime(TimeUtil.now());
      } else {
         project = projectService.getProjectById(projectDto.getProjectId());
      }
      project.setTitle(projectDto.getTitle());
      project.setDescription(projectDto.getDescription());
      project.setRepoUrl(projectDto.getRepoUrl());
      return project;
   }

   private static class ProjectSnapshotDto implements Dto {
      private long projectId;
      private String Title;

      public ProjectSnapshotDto(Project project) {
         setTitle(project.getTitle());
         setProjectId(project.getUidPk());
      }

      public long getProjectId() {
         return projectId;
      }

      public void setProjectId(long projectId) {
         this.projectId = projectId;
      }

      public String getTitle() {
         return Title;
      }

      public void setTitle(String title) {
         Title = title;
      }
   }

   private static class ProjectItemDto implements Dto {

   }

   private Ticket convertTicketDto(TicketDto ticketDto) {
      Ticket ticket;
      if (ticketDto.getTicketId() != 0) {
         ticket = ticketService.getTicketById(ticketDto.getTicketId());
      } else {
         ticket = new Ticket();
         ticket.setCreateTime(TimeUtil.now());
         ticket.setCreateUser(userService.getUserById(ticketDto
               .getReporterUserId()));
      }
      ticket.setLastModifyTime(TimeUtil.now());
      ticket.setAssignUser(userService.getUserById(ticketDto.getAssignUserId()));
      ticket.setDescription(ticketDto.getDescription());
      ticket.setProject(projectService.getProjectById(ticketDto.getProjectId()));
      ticket.setTitle(ticketDto.getTitle());
      if (ticketDto.getTicketType().equalsIgnoreCase("request")) {
         ticket.setTicketType(TicketConstant.REQUEST_TYPE);
      } else if (ticketDto.getTicketType().equalsIgnoreCase("bug")) {
         ticket.setTicketType(TicketConstant.BUG_TYPE);
      } else {
         throw new IllegalArgumentException(String.format(
               "Ticket Type illegal : \"%s\".", ticketDto.getTicketType()));
      }
      ticket.setPriority(ticketDto.getActualPriority());
      if (ticketDto.getDeadline() == null || ticketDto.getDeadline().equals("")) {
         return ticket;
      }
      ticket.setEndTime(TimeUtil.fromString(ticketDto.getDeadline()));
      return ticket;
   }

   private static class TicketDto implements Dto {
      private long ticketId;
      private String ticketType;
      private long projectId;
      private String title;
      private String description;
      private long assignUserId;
      private long reporterUserId;
      private String priority;
      private String deadline;

      public String getDeadline() {
         return deadline;
      }

      public void setDeadline(String deadline) {
         this.deadline = deadline;
      }

      public long getReporterUserId() {
         return reporterUserId;
      }

      public int getActualPriority() {
         if (getPriority().equalsIgnoreCase("p0")) {
            return TicketConstant.BUG_PRIORITY_P0;
         } else if (getPriority().equalsIgnoreCase("p1")) {
            return TicketConstant.BUG_PRIORITY_P1;
         } else if (getPriority().equalsIgnoreCase("p2")) {
            return TicketConstant.BUG_PRIORITY_P2;
         } else if (getPriority().equalsIgnoreCase("p3")) {
            return TicketConstant.BUG_PRIORITY_P3;
         } else {
            throw new IllegalArgumentException(String.format(
                  "Unknown priority : \"%s\".", getPriority()));
         }
      }

      public void setReporterUserId(long reporterUserId) {
         this.reporterUserId = reporterUserId;
      }

      public long getProjectId() {
         return projectId;
      }

      public void setProjectId(long projectId) {
         this.projectId = projectId;
      }

      public long getTicketId() {
         return ticketId;
      }

      public void setTicketId(long ticketId) {
         this.ticketId = ticketId;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getDescription() {
         return description;
      }

      public void setDescription(String description) {
         this.description = description;
      }

      public long getAssignUserId() {
         return assignUserId;
      }

      public void setAssignUserId(long assignUserId) {
         this.assignUserId = assignUserId;
      }

      public String getTicketType() {
         return ticketType;
      }

      public void setTicketType(String ticketType) {
         this.ticketType = ticketType;
      }

      public String getPriority() {
         return priority;
      }

      public void setPriority(String priority) {
         this.priority = priority;
      }
   }

   private static class TicketItemDto implements Dto {
      private long ticketId;
      private String ticketType;
      private String priority;
      private String title;
      private String description;
      private String deadline;

      public TicketItemDto(Ticket ticket) {
         setDescription(ticket.getDescription());
         setDeadline(TimeUtil.formatTime(ticket.getEndTime()));
         setPriority(generatePriority(ticket.getPriority()));
         setTitle(ticket.getTitle());
         setTicketType(generateTicketType(ticket.getTicketType()));
         setTicketId(ticket.getUidPk());
      }

      private String generatePriority(int priority) {
         return String.format("P%d", priority);
      }

      private String generateTicketType(int type) {
         if (type == TicketConstant.BUG_TYPE) {
            return "BUG";
         } else if (type == TicketConstant.REQUEST_TYPE) {
            return "REQUEST";
         } else {
            throw new IllegalArgumentException(String.format(
                  "Unknown Ticket type : \"%d\".", type));
         }
      }

      public long getTicketId() {
         return ticketId;
      }

      public void setTicketId(long ticketId) {
         this.ticketId = ticketId;
      }

      public String getTicketType() {
         return ticketType;
      }

      public void setTicketType(String ticketType) {
         this.ticketType = ticketType;
      }

      public String getPriority() {
         return priority;
      }

      public void setPriority(String priority) {
         this.priority = priority;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getDescription() {
         return description;
      }

      public void setDescription(String description) {
         this.description = description;
      }

      public String getDeadline() {
         return deadline;
      }

      public void setDeadline(String deadline) {
         this.deadline = deadline;
      }
   }
}
