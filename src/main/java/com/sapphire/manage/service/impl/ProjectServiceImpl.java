package com.sapphire.manage.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.manage.domain.Project;
import com.sapphire.manage.repository.ProjectRepository;
import com.sapphire.manage.service.ProjectService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public long saveProject(Project project) {
        return projectRepository.save(project).getUidPk();
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = projectRepository.getAllProjects();
        if (projects == null || projects.isEmpty()) {
            return Collections.emptyList();
        }
        return projects;
    }

    @Override
    public Project getProjectById(long uidPk) {
        Project project = projectRepository.findOne(uidPk);
        if (project == null) {
            throw new EntityNotFoundException(String.format("Project : \"%d\" not found.", uidPk));
        }
        return project;
    }
}
