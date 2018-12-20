package com.crewlance.server.graphql.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.crewlance.server.model.User;
import com.crewlance.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryResolver implements GraphQLQueryResolver {

    private final UserService userService;

    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }

    public List<User> users() {
        return userService.findAll();
    }
}
