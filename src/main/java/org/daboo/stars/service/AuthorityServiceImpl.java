package org.daboo.stars.service;

import org.daboo.stars.domain.entity.Authority;
import org.daboo.stars.repo.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public List<Authority> getAll() {
        List<Authority> authorities = new ArrayList<>();
        authorityRepository.findAll().forEach(authorities::add);
        return authorities;
    }

    @Override
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }
}
