package com.crewlance.server.config;

import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.crewlance.server.error.GraphQLErrorAdapter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import javax.servlet.Filter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class GraphQLConfig {

    @Bean
    public SchemaParserOptions schemaParserOptions() {
        return SchemaParserOptions.newOptions()
                .objectMapperConfigurer((mapper, context) -> {
                    mapper.registerModule(new JavaTimeModule())
                            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                })
                .build();
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {

            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                return Stream.concat(
                        errors.stream()
                                .filter(this::isClientError),
                        errors.stream()
                                .filter(e -> !isClientError(e))
                                .map(GraphQLErrorAdapter::new))
                        .collect(Collectors.toUnmodifiableList());
            }

            private boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }

    @Bean
    public Filter OpenFilter() {
        return new OpenEntityManagerInViewFilter();
    }
}
