package com.crewlance.server.model;

import com.crewlance.server.model.enums.PreferenceType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Preference extends DomainObject {

    private static final long serialVersionUID = -8378340249190197772L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferenceType type;

    @Column(length = 250)
    private String notes;

    @OneToMany(mappedBy = "preference", fetch = FetchType.EAGER)
    private Set<PreferenceKeyword> keywords;

    @OneToMany(mappedBy = "preference", fetch = FetchType.EAGER)
    private Set<PreferenceUser> friends;

    @ManyToOne(optional = false)
    private User user;

    public Preference() {
    }

    public Preference(PreferenceType type, String notes) {
        this.type = type;
        this.notes = notes;
    }
}
