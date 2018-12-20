package com.crewlance.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.*;

@Entity
@Getter
@Setter
public class Availability extends DomainObject {

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime start;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime end;

    @Column(length = 250)
    private String notes;

    @ManyToOne(optional = false)
    private User user;

    public Availability() {
    }

    public Availability(User user, LocalDateTime start, LocalDateTime end, String notes) {
        this.user = user;
        this.start = start;
        this.end = end;
        this.notes = notes;
    }
}
