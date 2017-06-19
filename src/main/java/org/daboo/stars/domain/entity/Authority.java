package org.daboo.stars.domain.entity;

import lombok.Data;
import lombok.ToString;
import org.daboo.stars.domain.enums.AuthorityName;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(exclude = "users")
public class Authority extends AbstractDomainClass {

    @Enumerated(EnumType.STRING)
    private AuthorityName authorityName;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
        if (!user.getAuthorities().contains(this)) {
            user.getAuthorities().add(this);
        }
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getAuthorities().remove(this);
    }
}