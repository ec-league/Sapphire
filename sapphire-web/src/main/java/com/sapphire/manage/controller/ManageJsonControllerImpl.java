package com.sapphire.manage.controller;

import java.util.ArrayList;
import java.util.List;

import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.dto.Dto;
import com.sapphire.common.utils.dto.JsonDto;
import com.sapphire.common.utils.dto.ListJsonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.manage.constant.TicketPriority;
import com.sapphire.manage.constant.TicketStatus;
import com.sapphire.manage.constant.TicketType;
import com.sapphire.manage.domain.Project;
import com.sapphire.manage.domain.Ticket;
import com.sapphire.manage.service.ProjectService;
import com.sapphire.manage.service.TicketService;
import com.sapphire.user.domain.User;
import com.sapphire.user.service.UserService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/rest/manage")
public class ManageJsonControllerImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageJsonControllerImpl.class);

    @Autowired
    private ProjectService      projectService;
    @Autowired
    private TicketService       ticketService;
    @Autowired
    private UserService         userService;

    @RequestMapping("/project/save.ep")
    @ResponseBody
    public JsonDto saveProject(@RequestBody ProjectDto projectDto) {
        try {
            Project project = convertProjectDto(projectDto);
            projectService.saveProject(project);
            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/project/list.ep")
    @ResponseBody
    public JsonDto getAllProjects() {
        return new JsonDto().formSuccessDto();
    }

    @RequestMapping("/project/snapshots/list.ep")
    @ResponseBody
    public JsonDto getAllProjectSnapshots() {
        try {
            List<Project> projects = projectService.getAllProjects();
            List<ProjectSnapshotDto> dtos = new ArrayList<ProjectSnapshotDto>();
            for (Project project : projects) {
                dtos.add(new ProjectSnapshotDto(project));
            }
            return new ListJsonDto<ProjectSnapshotDto>(dtos).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/user/snapshots/list.ep")
    @ResponseBody
    public JsonDto getAllUserSnapshots() {
        try {
            List<User> users = userService.getUsers();
            List<UserSnapshotDto> dtos = new ArrayList<UserSnapshotDto>();
            for (User u : users) {
                dtos.add(new UserSnapshotDto(u));
            }
            return new ListJsonDto<UserSnapshotDto>(dtos).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/ticket/getByUser.ep")
    @ResponseBody
    public JsonDto getTicketsByUserId() {
        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<TicketItemDto> dtos = new ArrayList<TicketItemDto>();
            for (Ticket ticket : ticketService.getTicketsByReportUserId(u.getUidPk())) {
                dtos.add(new TicketItemDto(ticket));
            }
            return new ListJsonDto<TicketItemDto>(dtos).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/ticket/{projectId}/getByProject.ep")
    @ResponseBody
    public JsonDto getTicketsByProjectId(@PathVariable("projectId") long projectId) {
        try {
            List<TicketItemDto> dtos = new ArrayList<>();
            for (Ticket ticket : ticketService.getTicketsByProjectId(projectId)) {
                dtos.add(new TicketItemDto(ticket));
            }
            return new ListJsonDto<TicketItemDto>(dtos).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/ticket/save.ep")
    @ResponseBody
    public JsonDto saveTicket(@RequestBody TicketDto ticketDto) {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
            ticketDto.setReporterUserId(user.getUidPk());

            Ticket ticket = convertTicketDto(ticketDto);
            ticketService.saveTicket(ticket);
            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    private static class ProjectDto implements Dto {
        private long   projectId;
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
        private long   projectId;
        private String title;

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
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    private static class UserSnapshotDto implements Dto {
        private long   userId;
        private String username;

        public UserSnapshotDto(User u) {
            setUsername(u.getUsername());
            setUserId(u.getUidPk());
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
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
            ticket.setCreateUser(userService.getUserById(ticketDto.getReporterUserId()));
        }
        ticket.setLastModifyTime(TimeUtil.now());
        ticket.setAssignUser(userService.getUserById(ticketDto.getAssignUserId()));
        ticket.setDescription(ticketDto.getDescription());
        ticket.setProject(projectService.getProjectById(ticketDto.getProjectId()));
        ticket.setTitle(ticketDto.getTitle());
        ticket.setTicketType(TicketType.toTicketType(ticketDto.getTicketType()));
        ticket.setTicketPriority(ticketDto.getActualPriority());
        if (ticketDto.getDeadline() == null || "".equals(ticketDto.getDeadline())) {
            return ticket;
        }
        ticket.setEndTime(TimeUtil.fromString(ticketDto.getDeadline()));
        return ticket;
    }

    private static class TicketDto implements Dto {
        private long   ticketId;
        private String ticketType;
        private long   projectId;
        private String title;
        private String description;
        private long   assignUserId;
        private long   reporterUserId;
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

        public TicketPriority getActualPriority() {
            return TicketPriority.toTicketPriority(getPriority());
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
        private long   ticketId;
        private String ticketType;
        private String priority;
        private String title;
        private String description;
        private String deadline;
        private String status;

        public TicketItemDto(Ticket ticket) {
            setDescription(ticket.getDescription());
            setDeadline(TimeUtil.formatTime(ticket.getEndTime()));
            setPriority(ticket.getTicketPriority().toString());
            setTitle(ticket.getTitle());
            setTicketType(ticket.getTicketType().toString());
            setTicketId(ticket.getUidPk());
            setStatus(TicketStatus.getTicketStatus(ticket.getStatus()));
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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
