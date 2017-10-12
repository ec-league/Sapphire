package com.sapphire.service.manage;

import javax.persistence.EntityNotFoundException;

import com.sapphire.biz.manage.service.ProjectService;
import com.sapphire.biz.manage.service.TicketService;
import com.sapphire.common.utils.TimeUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.common.dal.manage.constant.TicketPriority;
import com.sapphire.common.dal.manage.constant.TicketType;
import com.sapphire.common.dal.manage.domain.Project;
import com.sapphire.common.dal.manage.domain.Ticket;
import com.sapphire.common.dal.user.domain.User;
import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.service.UserService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
public class TicketServiceTest extends BaseTest {
   @Autowired
   private TicketService ticketService;

   @Autowired
   private UserService userService;

   @Autowired
   private ProjectService projectService;

   private User createUser;
   private User assignUser;
   private Project project;

   @Before
   public void init() {
      UserDto dto = new UserDto();
      String username = RandomStringUtils.randomAlphabetic(10);
      String password = RandomStringUtils.randomAlphabetic(10);
      String email =
            String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5),
                  RandomStringUtils.randomAlphanumeric(5));
      dto.setUsername(username);
      dto.setPassword(password);
      dto.setEmail(email);
      long userId = userService.createUser(dto);
      createUser = userService.getUserById(userId);
      dto.setUsername(RandomStringUtils.randomAlphabetic(12));
      dto.setEmail(RandomStringUtils.randomAlphanumeric(12));
      userId = userService.createUser(dto);
      assignUser = userService.getUserById(userId);
      project = new Project();
      project.setCreateTime(TimeUtil.now());
      project.setLastModifyTime(TimeUtil.now());
      project.setTitle(RandomStringUtils.randomAlphabetic(5));
      project.setRepoUrl(RandomStringUtils.randomAlphanumeric(20));
      long projectId = projectService.saveProject(project);
      project = projectService.getProjectById(projectId);
   }

   @Test
   public void testNormal() {
      Assert.assertTrue(ticketService.getTicketsByReportUserId(
            createUser.getUidPk()).isEmpty());
      Assert.assertTrue(ticketService.getTicketsByAssignUserId(
            assignUser.getUidPk()).isEmpty());
      Assert.assertTrue(ticketService.getTicketsByProjectId(project.getUidPk())
            .isEmpty());

      Ticket ticket = new Ticket();
      ticket.setCreateTime(TimeUtil.now());
      ticket.setLastModifyTime(TimeUtil.now());
      ticket.setTicketType(TicketType.REQUEST);
      ticket.setDescription(RandomStringUtils.randomAlphanumeric(5));
      ticket.setTitle(RandomStringUtils.randomAlphabetic(6));
      ticket.setCreateUser(createUser);
      ticket.setAssignUser(assignUser);
      ticket.setProject(project);
      ticket.setStatus(0);
      ticket.setTicketPriority(TicketPriority.P1);
      ticket.setStartTime(TimeUtil.now());
      ticket.setEndTime(TimeUtil.now());

      long ticketId = ticketService.saveTicket(ticket);
      Assert.assertFalse(ticketService.getTicketsByReportUserId(
            createUser.getUidPk()).isEmpty());
      Assert.assertFalse(ticketService.getTicketsByAssignUserId(
            assignUser.getUidPk()).isEmpty());
      Assert.assertFalse(ticketService
            .getTicketsByProjectId(project.getUidPk()).isEmpty());

      Ticket testTicket = ticketService.getTicketById(ticketId);

      Assert.assertEquals(ticket.getDescription(), testTicket.getDescription());
      Assert.assertEquals(ticket.getTitle(), testTicket.getTitle());

   }

   @Test(expected = EntityNotFoundException.class)
   public void testAbnormal() {
      ticketService.getTicketById(0);
   }
}
