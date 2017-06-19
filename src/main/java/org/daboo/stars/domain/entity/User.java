package org.daboo.stars.domain.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString(exclude = "authorities")
public class User extends AbstractDomainClass {

    public User() {
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    private String username;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority authority) {
        if (!this.getAuthorities().contains(authority)) {
            this.getAuthorities().add(authority);
        }
        if (!authority.getUsers().contains(this)) {
            authority.getUsers().add(this);
        }
    }

    public void removeAuthority(Authority authority) {
        this.getAuthorities().remove(authority);
        authority.getUsers().remove(this);
    }
}