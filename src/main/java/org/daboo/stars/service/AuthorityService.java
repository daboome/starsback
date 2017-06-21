package org.daboo.stars.service;

import org.daboo.stars.domain.entity.Authority;

import java.util.List;

public interface AuthorityService {
    List<Authority> getAll();

    void saveAuthority(Authority authority);
}
