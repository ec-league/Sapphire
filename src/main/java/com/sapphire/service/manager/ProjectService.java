package com.sapphire.service.manager;

import com.sapphire.domain.manage.Project;

import java.util.List;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/24.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface ProjectService {
   long saveProject(Project project);

   List<Project> getAllProjects();
}
