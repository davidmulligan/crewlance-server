package com.crewlance.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PreferenceKeyword extends DomainObject {


    private static final long serialVersionUID = 8316514718704052629L;
    @Column(length = 100, nullable = false)
    private String value;

    @Column(length = 250)
    private String notes;

    @ManyToOne(optional = false)
    private Preference preference;

    public PreferenceKeyword() {
    }

    public PreferenceKeyword(String value, String notes) {
        this.value = value;
        this.notes = notes;
    }
}
