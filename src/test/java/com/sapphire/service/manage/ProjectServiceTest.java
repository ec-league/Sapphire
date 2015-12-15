package com.sapphire.service.manage;

import com.sapphire.common.TimeUtil;
import com.sapphire.domain.manage.Project;
import com.sapphire.service.manager.ProjectService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityNotFoundException;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class ProjectServiceTest extends AbstractTestNGSpringContextTests {
   @Autowired
   private ProjectService projectService;

   @Test
   public void test() {
      Project project = new Project();
      project.setRepoUrl(RandomStringUtils.randomAlphanumeric(20));
      project.setDescription(RandomStringUtils.randomAlphanumeric(50));
      project.setTitle(RandomStringUtils.randomAlphabetic(10));
      project.setCreateTime(TimeUtil.now());
      project.setLastModifyTime(TimeUtil.now());
      int length = projectService.getAllProjects().size();

      long projectId = projectService.saveProject(project);
      Project testProject = projectService.getProjectById(projectId);

      Assert.assertEquals(length + 1, projectService.getAllProjects().size());
      Assert.assertEquals(testProject.getRepoUrl(), project.getRepoUrl());
      Assert.assertEquals(testProject.getTitle(), project.getTitle());
      Assert.assertEquals(testProject.getDescription(),
            project.getDescription());
   }

   @Test(expectedExceptions = EntityNotFoundException.class, expectedExceptionsMessageRegExp = "Project : \"[0-9]+\" not found.")
   public void testEmpty() {
      projectService.getProjectById(0);
   }
}
