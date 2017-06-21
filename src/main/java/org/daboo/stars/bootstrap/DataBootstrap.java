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
    @Autowired
    StarRepository starRepository;
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
        User user = new User("user", passwordEncoder.encode("password"));
        List<Authority> authorities = authorityService.getAll();
        authorities.forEach(authority -> {
            if (authority.getAuthorityName().equalsName(AuthorityName.ROLE_USER.name())) {
                user.addAuthority(authority);
                userRepository.save(user);
            }
        });
    }

    private void loadStars() {
        Star star1 = new Star("starName1", "xcoord1", "ycoord1", StarType.BLUE, "pesron1");
        Star star2 = new Star("starName2", "xcoord2", "ycoord2", StarType.WHITE_BLUE, "pesron2");
        Star star3 = new Star("starName3", "xcoord3", "ycoord3", StarType.WHITE, "pesron3");
        Star star4 = new Star("starName4", "xcoord4", "ycoord4", StarType.YELLOW_WHITE, "pesron4");
        Star star5 = new Star("starName5", "xcoord5", "ycoord5", StarType.YELLOW, "pesron5");
        Star star6 = new Star("starName6", "xcoord6", "ycoord6", StarType.ORANGE, "pesron6");
        Star star7 = new Star("starName7", "xcoord7", "ycoord7", StarType.RED, "pesron7");

        starRepository.save(star1);
        starRepository.save(star2);
        starRepository.save(star3);
        starRepository.save(star4);
        starRepository.save(star5);
        starRepository.save(star6);
        starRepository.save(star7);
    }
}
