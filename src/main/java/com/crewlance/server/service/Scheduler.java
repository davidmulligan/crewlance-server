package com.crewlance.server.service;

import com.crewlance.server.model.Allocation;
import com.crewlance.server.model.Project;
import com.crewlance.server.model.User;
import com.crewlance.server.model.enums.PreferenceType;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static org.jooq.lambda.Seq.seq;

@Component
public class Scheduler {

    private final UserService userService;

    public Scheduler(UserService userService) {
        this.userService = userService;
    }

    public Project scheduleProject(@NonNull Project project) {

        List<User> keywordUsers = seq(userService.findAll())
                .filter(user -> seq(user.getPreferences())
                        .anyMatch(preference -> preference.getType().equals(PreferenceType.KEYWORD)
                                && seq(preference.getKeywords())
                                .map(preferenceKeyword -> preferenceKeyword.getValue())
                                .anyMatch(seq(project.getKeywords())
                                        .map(projectKeyword -> projectKeyword.getValue())
                                        .toList()::contains)))
                .toList();

        List<User> worksWithUsers = seq(userService.findAll())
                .filter(user -> seq(user.getPreferences())
                        .anyMatch(preference -> preference.getType().equals(PreferenceType.WORK_WITH)
                                && seq(preference.getFriends()).anyMatch(keywordUsers::contains)))
                .toList();

        Stream.concat(seq(keywordUsers), seq(worksWithUsers))
                .distinct()
                .forEach(user -> {
                    double count = 0;
                    if (keywordUsers.contains(user)) {
                        count = count + 1;
                    }
                    if (worksWithUsers.contains(user)) {
                        count = count + 1;
                    }
                    double strength = count / 2d * 100;
                    project.getAllocations().add(new Allocation(user, project, project.getStart(), project.getEnd(), strength));

                });

        return project;
    }
}
