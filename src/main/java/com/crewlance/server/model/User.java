package com.crewlance.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends DomainObject {

    private static final long serialVersionUID = -4117579343433317526L;

    @Column(length = 50, nullable = false)
    private String firstName;

    @Column(length = 50, nullable = false)
    private String lastName;

    @Column(length = 250, nullable = false, unique = true)
    private String email;

    @Column(length = 20, nullable = false)
    private String telephone1;

    @Column(length = 20)
    private String telephone2;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Preference> preferences;

    public User() {
    }

    public User(String firstName, String lastName, String email, String telephone1, String telephone2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }

    @Transient
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
