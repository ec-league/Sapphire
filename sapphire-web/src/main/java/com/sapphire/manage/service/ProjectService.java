package com.sapphire.manage.service;

import java.util.List;

import com.sapphire.manage.domain.Project;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/24.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface ProjectService {
    long saveProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(long uidPk);
}
