package com.crewlance.server.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.crewlance.server.model.Project;
import com.crewlance.server.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectQueryResolver implements GraphQLQueryResolver {

    private final ProjectService projectService;

    public ProjectQueryResolver(ProjectService projectService) {
        this.projectService = projectService;
    }

    public List<Project> projects() {
        return projectService.findAll();
    }
}
