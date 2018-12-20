package com.crewlance.server.graphql.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.crewlance.server.model.User;
import com.crewlance.server.service.UserService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserService userService;

    public UserMutationResolver(UserService userService) {
        this.userService = userService;
    }

    public User createUser(@NonNull String firstName, @NonNull String lastName, @NonNull String email,
                           @NonNull String telephone1, String telephone2) {
        return userService.create(new User(firstName, lastName, email, telephone1, telephone2));
    }

    public User updateUser(@NonNull String id, Optional<String> firstName, Optional<String> lastName,
                           Optional<String> email, Optional<String> telephone1, Optional<String> telephone2) {
        User user = userService.findById(id);
        user.setFirstName(firstName.orElse(user.getFirstName()));
        user.setLastName(lastName.orElse(user.getLastName()));
        user.setEmail(email.orElse(user.getEmail()));
        user.setTelephone1(telephone1.orElse(user.getTelephone1()));
        user.setTelephone2(telephone2.orElse(user.getTelephone2()));
        return userService.update(user);
    }

    public String deleteUser(@NonNull String id) {
        return userService.delete(userService.findById(id));
    }
}