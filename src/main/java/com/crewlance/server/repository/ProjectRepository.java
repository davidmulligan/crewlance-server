package com.crewlance.server.repository;

import com.crewlance.server.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, String> {
}
