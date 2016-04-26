package com.sapphire.manage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapphire.manage.domain.Project;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
   @Query("select p from Project as p order by p.uidPk")
   List<Project> getAllProjects();
}
