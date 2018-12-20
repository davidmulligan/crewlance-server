package com.crewlance.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

@Entity
@Getter
@Setter
public class Allocation extends DomainObject {

    private static final long serialVersionUID = -1508103606577604479L;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime start;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime end;

    @Column(nullable = false)
    private Double strength;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Project project;

    public Allocation() {
    }

    public Allocation(User user, Project project, LocalDateTime start, LocalDateTime end, Double strength) {
        this.user = user;
        this.project = project;
        this.start = start;
        this.end = end;
        this.strength = strength;
    }
}
