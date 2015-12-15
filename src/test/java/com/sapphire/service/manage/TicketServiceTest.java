package com.sapphire.service.manage;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sapphire.common.TimeUtil;
import com.sapphire.constant.TicketPriority;
import com.sapphire.constant.TicketType;
import com.sapphire.domain.User;
import com.sapphire.domain.manage.Project;
import com.sapphire.domain.manage.Ticket;
import com.sapphire.dto.user.UserDto;
import com.sapphire.service.manager.ProjectService;
import com.sapphire.service.manager.TicketService;
import com.sapphire.service.user.UserService;

import javax.persistence.EntityNotFoundException;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TicketServiceTest extends AbstractTestNGSpringContextTests {
   @Autowired
   private TicketService ticketService;

   @Autowired
   private UserService userService;

   @Autowired
   private ProjectService projectService;

   private User createUser;
   private User assignUser;
   private Project project;

   @BeforeClass
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
      Assert.assertFalse(ticketService.getTicketsByProjectId(project.getUidPk())
            .isEmpty());

      Ticket testTicket = ticketService.getTicketById(ticketId);

      Assert.assertEquals(ticket.getDescription(), testTicket.getDescription());
      Assert.assertEquals(ticket.getTitle(), testTicket.getTitle());

   }

   @Test(expectedExceptions = EntityNotFoundException.class)
   public void testAbnormal() {
      ticketService.getTicketById(0);
   }
}
