package org.daboo.stars.bootstrap;

import org.daboo.stars.domain.entity.Authority;
import org.daboo.stars.domain.entity.Star;
import org.daboo.stars.domain.entity.User;
import org.daboo.stars.domain.enums.AuthorityName;
import org.daboo.stars.domain.enums.StarType;
import org.daboo.stars.repo.StarRepository;
import org.daboo.stars.repo.UserRepository;
import org.daboo.stars.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private int starsCount = 7;
    private String userName = "user";
    private String userPassword = "password";

    @Autowired
    StarRepository starRepository;

    public Integer getStarsCount() {
        return starsCount;
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (authorityService.getAll().isEmpty()) loadAuthorities();
        if (starRepository.findAll().isEmpty()) loadStars();
        if (userRepository.findAll().isEmpty()) loadUsers();
    }

    private void loadAuthorities() {
        Authority authority1 = new Authority();
        authority1.setAuthorityName(AuthorityName.ROLE_USER);
        authorityService.saveAuthority(authority1);
        Authority authority2 = new Authority();
        authority2.setAuthorityName(AuthorityName.ROLE_ADMIN);
        authorityService.saveAuthority(authority2);
    }

    private void loadUsers() {
        User user = new User(userName, passwordEncoder.encode(userPassword));
        List<Authority> authorities = authorityService.getAll();
        authorities.forEach(authority -> {
            if (authority.getAuthorityName().equalsName(AuthorityName.ROLE_USER.name())) {
                user.addAuthority(authority);
                userRepository.save(user);
            }
        });
    }

    private void loadStars() {
        for (int i = 0; i < starsCount; i ++) {
            Star star = new Star("starName_" + i, "xcoord_" + i, "ycoord_" + i, StarType.values()[i], "pesron_" + i);
            starRepository.save(star);
        }
    }
}
