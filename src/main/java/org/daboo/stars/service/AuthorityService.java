package org.daboo.stars.service;

import org.daboo.stars.domain.entity.Authority;

import java.util.List;

/**
 * Created by daboo on 6/19/17.
 */
public interface AuthorityService {
    List<Authority> getAll();

    void saveAuthority(Authority authority);
}
