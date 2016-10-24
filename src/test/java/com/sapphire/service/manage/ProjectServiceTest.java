package com.sapphire.service.manage;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.manage.domain.Project;
import com.sapphire.manage.service.ProjectService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
public class ProjectServiceTest extends BaseTest {
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

   @Test(expected = EntityNotFoundException.class)
   public void testEmpty() {
      projectService.getProjectById(0);
   }
}
