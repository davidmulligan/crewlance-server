package com.crewlance.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PreferenceUser extends DomainObject {

    private static final long serialVersionUID = -2523803426793515882L;

    @Column(length = 250)
    private String notes;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Preference preference;

    public PreferenceUser() {
    }

    public PreferenceUser(User user, String notes) {
        this.user = user;
        this.notes = notes;
    }
}
