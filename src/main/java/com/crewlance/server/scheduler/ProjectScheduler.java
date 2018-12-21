package com.crewlance.server.scheduler;

import com.crewlance.server.model.Allocation;
import com.crewlance.server.model.Project;
import com.crewlance.server.model.ProjectKeyword;
import com.crewlance.server.model.User;
import com.crewlance.server.service.UserService;
import lombok.NonNull;
import org.jooq.lambda.Seq;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.crewlance.server.model.enums.PreferenceType.KEYWORD;
import static com.crewlance.server.model.enums.PreferenceType.WORK_WITH;
import static org.jooq.lambda.Seq.seq;

@Component
public class ProjectScheduler {

    private final UserService userService;

    public ProjectScheduler(UserService userService) {
        this.userService = userService;
    }

    public void scheduleProject(@NonNull Project project) {
        List<User> users = userService.findAll();
        Set<User> keywordUsers = filterKeywords(seq(users), project.getKeywords());
        Set<User> worksWithUsers = filterWorksWith(seq(users), keywordUsers);
        List<Allocation> allocations = generateAllocations(project, Arrays.asList(keywordUsers, worksWithUsers));
        project.getAllocations().addAll(allocations);
    }

    private static Set<User> filterKeywords(Seq<User> users, Set<ProjectKeyword> keywords) {
        return users
                .filter(user -> seq(user.getPreferences()).anyMatch(preference -> preference.getType().equals(KEYWORD)))
                .filter(user -> seq(user.getPreferences()).anyMatch(preference -> seq(preference.getKeywords())
                        .map(preferenceKeyword -> preferenceKeyword.getValue())
                        .anyMatch(seq(keywords).map(projectKeyword -> projectKeyword.getValue()).toList()::contains)))
                .toSet();
    }

    private static Set<User> filterWorksWith(Seq<User> users, Set<User> allocatedUsers) {
        return users
                .filter(user -> seq(user.getPreferences()).anyMatch(preference -> preference.getType().equals(WORK_WITH)))
                .filter(user -> seq(user.getPreferences()).anyMatch(preference -> seq(preference.getFriends()).anyMatch(allocatedUsers::contains)))
                .toSet();
    }

    private static List<Allocation> generateAllocations(Project project, List<Set<User>> dataset) {
        List<User> users = seq(dataset).flatMap(itm -> itm.stream()).toList();
        return seq(users)
                .distinct()
                .map(user -> new Allocation(user, project, project.getStart(), project.getEnd(), (Collections.frequency(users, user) / (double) dataset.size() * 100)))
                .toList();
    }
}