package com.crewlance.server.model;

import com.crewlance.server.model.enums.ProjectStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Project extends DomainObject {

    private static final long serialVersionUID = 8868079707959434921L;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(length = 100, nullable = false)
    private String location;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime start;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ProjectKeyword> keywords;

    @OneToMany(mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Allocation> allocations;

    public Project() {
        status = ProjectStatus.NEW;
    }

    public Project(String title, String description, String location, LocalDateTime start, LocalDateTime end) {
        this();
        this.title = title;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
    }
}