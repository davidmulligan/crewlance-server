package com.crewlance.server.graphql.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

@Component
public class DateTime extends GraphQLScalarType {

    private static final String DEFAULT_NAME = "DateTime";

    public DateTime() {
        this(DEFAULT_NAME);
    }

    public DateTime(String name) {
        super(name, "DateTime type", new LocalDateTimeConverter());
    }

    static class LocalDateTimeConverter implements Coercing<LocalDateTime, String> {

        private static final DateTimeFormatter ISO_LOCAL_DATE_TIME_FORMATTER = ISO_LOCAL_DATE_TIME.withZone(ZoneOffset.UTC);

        private static LocalDateTime convertImpl(Object input) {
            if (input instanceof String) {
                return parseDateTime((String) input);
            }
            return null;
        }

        @Override
        public String serialize(Object input) {
            if (input instanceof LocalDateTime) {
                return toIsoString((LocalDateTime) input);
            } else {
                LocalDateTime result = convertImpl(input);
                if (result == null) {
                    throw new CoercingSerializeException(String.format("Invalid value '%s' for LocalDateTime", input));
                }
                return toIsoString(result);
            }
        }

        @Override
        public LocalDateTime parseValue(Object input) {
            LocalDateTime result = convertImpl(input);
            if (result == null) {
                throw new CoercingParseValueException(String.format("Invalid value '%s' for LocalDateTime", input));
            }
            return result;
        }

        @Override
        public LocalDateTime parseLiteral(Object input) {
            if (!(input instanceof StringValue)) {
                return null;
            }
            return convertImpl(((StringValue) input).getValue());
        }

        private static String toIsoString(LocalDateTime dateTime) {
            Assert.notNull(dateTime, "You must provide a dateTime");
            return ISO_LOCAL_DATE_TIME_FORMATTER.format(dateTime);
        }

        private static LocalDateTime parseDateTime(String dateTime) {
            Assert.notNull(dateTime, "You must provide a dateTime");
            try {
                return LocalDateTime.parse(dateTime, ISO_LOCAL_DATE_TIME_FORMATTER);
            } catch (DateTimeParseException ignored) {
                // Ignored
            }

            return null;
        }
    }
}
