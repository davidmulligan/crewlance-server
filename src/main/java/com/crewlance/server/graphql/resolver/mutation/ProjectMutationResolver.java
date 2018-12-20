package com.crewlance.server.graphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.crewlance.server.model.Project;
import com.crewlance.server.service.ProjectService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProjectMutationResolver implements GraphQLMutationResolver {

    private final ProjectService projectService;

    public ProjectMutationResolver(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Project createProject(@NonNull String title, @NonNull String description, @NonNull String location,
                                 @NonNull LocalDateTime start, @NonNull LocalDateTime end) {
        return projectService.create(new Project(title, description, location, start, end));
    }

    public Project updateProject(@NonNull String id, Optional<String> title, Optional<String> description,
                                 Optional<String> location, Optional<LocalDateTime> start, Optional<LocalDateTime> end) {
        Project project = projectService.findById(id);

        project.setTitle(title.orElse(project.getTitle()));
        project.setDescription(description.orElse(project.getDescription()));
        project.setLocation(location.orElse(project.getLocation()));
        project.setStart(start.orElse(project.getStart()));
        project.setEnd(end.orElse(project.getEnd()));
        return projectService.update(project);
    }

    public Project scheduleProject(@NonNull String id) {
        Project project = projectService.findById(id);

        return projectService.schedule(project);
    }

    public String deleteProject(@NonNull String id) {
        return projectService.delete(projectService.findById(id));
    }
}