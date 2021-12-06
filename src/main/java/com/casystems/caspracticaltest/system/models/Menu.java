package com.casystems.caspracticaltest.system.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;
    @Column(nullable=false)
    private String link;

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
