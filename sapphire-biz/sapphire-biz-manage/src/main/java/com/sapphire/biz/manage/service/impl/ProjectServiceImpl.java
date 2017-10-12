package com.sapphire.biz.manage.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.sapphire.biz.manage.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.common.dal.manage.domain.Project;
import com.sapphire.common.dal.manage.repository.ProjectRepository;

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
